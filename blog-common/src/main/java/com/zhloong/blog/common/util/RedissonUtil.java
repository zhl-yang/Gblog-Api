package com.zhloong.blog.common.util;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBatch;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhloong
 * create time: 2021/8/10 23:27
 * @description RedissonClient utils
 */
@Slf4j
@Component
public class RedissonUtil {

    private RedissonClient redissonClient;

    private static volatile RedissonUtil instance;

    private RedissonUtil(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public static RedissonUtil getInstance(RedissonClient redissonClient) {
        if (instance == null) {
            synchronized (RedissonUtil.class) {
                if (instance == null) {
                    instance = new RedissonUtil(redissonClient);
                }
            }
        }
        return instance;
    }


    /**
     * <p>
     * 通过key获取储存在redis中的value
     * </p>
     * <p>
     * 并释放连接
     * </p>
     *
     * @param key
     * @return 成功返回value 失败返回null
     */
    public String get(String key) {
        return redissonClient.<String>getBucket(key, StringCodec.INSTANCE).get();
    }

    /**
     * <p>
     * 向redis存入key和value,并释放连接资源
     * </p>
     * <p>
     * 如果key已经存在 则覆盖
     * </p>
     *
     * @param key
     * @param value
     * @return 成功 返回OK 失败返回 0
     */
    public String set(String key, String value) {
        redissonClient.<String>getBucket(key, StringCodec.INSTANCE).set(value);
        return "OK";
    }

    /**
     * <p>
     * 向redis存入key和value,并释放连接资源
     * </p>
     * <p>
     * 如果key已经存在 则覆盖
     * </p>
     *
     * @param key
     * @param value
     * @param time
     * @return 成功 返回OK 失败返回 0
     */
    public String set(String key, String value, long time) {
        redissonClient.<String>getBucket(key, StringCodec.INSTANCE).set(value, time, TimeUnit.SECONDS);
        return "OK";
    }

    /**
     * <p>
     * 向redis存入key和value,并释放连接资源
     * </p>
     * <p>
     * 如果key已经存在 则覆盖
     * </p>
     *
     * @param key
     * @param value
     * @param time
     * @param unit
     * @return 成功 返回OK 失败返回 0
     */
    public String set(String key, String value, long time, TimeUnit unit) {
        redissonClient.<String>getBucket(key, StringCodec.INSTANCE).set(value, time, unit);
        return "OK";
    }

    /**
     * <p>
     * 以秒为单位，返回给定 key 的剩余生存时间
     * </p>
     *
     * @param key
     * @return 当 key 不存在时，返回 -2 。当 key 存在但没有设置剩余生存时间时，返回 -1 。否则，以秒为单位，返回 key
     * 的剩余生存时间。 发生异常 返回 0
     */
    public Long ttl(String key) {
        return redissonClient.getBucket(key).remainTimeToLive();
    }

    /**
     * <p>
     * 删除指定的key,也可以传入一个包含key的数组
     * </p>
     *
     * @param keys 一个key 也可以使 string 数组
     * @return 返回删除成功的个数
     */
    public Long del(String... keys) {
        RBatch rBatch = redissonClient.createBatch();
        for (String key : keys) {
            rBatch.getBucket(key, StringCodec.INSTANCE).deleteAsync();
        }
        return rBatch.execute().getResponses().stream().filter(r -> (Boolean) r).count();
    }

    /**
     * <p>
     * 判断key是否存在
     * </p>
     *
     * @param key
     * @return true OR false
     */
    public Boolean exists(String key) {
        return redissonClient.getBucket(key).isExists();
    }

    /**
     * <p>
     * 新增key,并将 key 的生存时间 (以秒为单位)
     * </p>
     *
     * @param key
     * @param seconds 生存时间 单位：秒 >0
     * @param value
     * @return 设置成功时返回 1 。当 seconds 参数不合法时，返回0。
     */
    public Integer setex(String key, int seconds, String value) {
        RBucket<Object> rBucket = redissonClient.getBucket(key, StringCodec.INSTANCE);
        int ret = 0;
        if (seconds > 0) {
            rBucket.set(value, new Long(seconds), TimeUnit.SECONDS);
        } else {
            return 0;
        }
        return 1;
    }

    /**
     * @param key
     * @param seconds
     * @Description 根据 key 设置 redis缓存有效时间
     * @Method expire
     * @Author hyzhang
     * @Exception
     * @Date 2021/1/29 17:34
     */
    public void expire(String key, int seconds) {
        if (seconds > 0) {
            this.redissonClient.getBucket(key).expire(seconds, TimeUnit.SECONDS);
        }
    }

    /**
     * <p>
     * 设置key value,如果key已经存在则返回0,nx==> not exist
     * </p>
     *
     * @param key
     * @param value
     * @return boolean
     */
    public boolean setNx(String key, String value) {
        return redissonClient.getBucket(key, StringCodec.INSTANCE).trySet(value);
    }

    /**
     * <p>
     * 设置key value,如果key已经存在则返回0,nx==> not exist
     * </p>
     *
     * @param key
     * @param value
     * @param seconds 过期时间 秒
     * @return boolean
     */
    public boolean setNx(String key, String value, int seconds) {
        if (seconds > 0) {
            return redissonClient.getBucket(key, StringCodec.INSTANCE).trySet(value, seconds, TimeUnit.SECONDS);
        } else {
            return setNx(key, value);
        }
    }

    /**
     * <p>
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
     * </p>
     * <p>
     * 当 key 存在但不是字符串类型时，返回一个错误。
     * </p>
     *
     * @param key
     * @param value
     * @return 返回给定 key 的旧值。当 key 没有旧值时，也即是， key 不存在时，返回 nil
     */
    public String getSet(String key, String value) {
        return redissonClient.<String>getBucket(key, StringCodec.INSTANCE).getAndSet(value);
    }

    /**
     * <p>
     * 通过批量的key获取批量的value
     * </p>
     *
     * @param keys string数组 也可以是一个key
     * @return 成功返回value的集合, 失败返回null的集合 ,异常返回空
     */
    @SuppressWarnings("unchecked")
    public List<String> mget(String... keys) {
        RBatch rBatch = redissonClient.createBatch();
        for (String key : keys) {
            rBatch.<String>getBucket(key, StringCodec.INSTANCE).getAsync();
        }
        return (List<String>) rBatch.execute().getResponses();
    }

    /**
     * <p>
     * 批量的设置key:value,可以一个
     * </p>
     * <p>
     * example:
     * </p>
     * <p>
     * obj.mset(new String[]{"key2","value1","key2","value2"})
     * </p>
     *
     * @param keysvalues
     * @return 成功返回OK 失败 异常 返回 null
     */
    public String mset(String... keysvalues) {
        try {
            RBatch rBatch = redissonClient.createBatch();
            for (int i = 0; i < keysvalues.length; ) {
                rBatch.getBucket(keysvalues[i], StringCodec.INSTANCE).setAsync(keysvalues[i + 1]);
                i = i + 2;
            }
            rBatch.execute();
        } catch (Exception e) {
            return null;
        }
        return "OK";
    }

    /**
     * <p>
     * 设置key的值,并返回一个旧值
     * </p>
     *
     * @param key
     * @param value
     * @return 旧值 如果key不存在 则返回null
     */
    public String getset(String key, String value) {
        return redissonClient.<String>getBucket(key, StringCodec.INSTANCE).getAndSet(value);
    }

    /**
     * <p>
     * 通过key 对value进行加值+1操作,当value不是int类型时会返回错误,当key不存在是则value为1
     * </p>
     *
     * @param key
     * @return 加值后的结果
     */
    public Long incr(String key) {
        return redissonClient.getAtomicLong(key).incrementAndGet();
    }

    /**
     * <p>
     * 通过key给指定的value加值,如果key不存在,则这是value为该值
     * </p>
     *
     * @param key
     * @param integer
     * @return
     */
    public Long incrBy(String key, Integer integer) {
        return redissonClient.getAtomicLong(key).addAndGet(integer);

    }

    /**
     * <p>
     * 对key的值做减减操作,如果key不存在,则设置key为-1
     * </p>
     *
     * @param key
     * @return
     */
    public Long decr(String key) {
        return redissonClient.getAtomicLong(key).decrementAndGet();

    }

    /**
     * <p>
     * 减去指定的值
     * </p>
     *
     * @param key
     * @param integer
     * @return
     */
    public Long decrBy(String key, Integer integer) {
        return redissonClient.getAtomicLong(key).addAndGet(0 - integer);
    }

    /**
     * <p>
     * 通过key获取value值的长度
     * </p>
     *
     * @param key
     * @return 失败返回null
     */
    public Long serlen(String key) {
        return redissonClient.<String>getBucket(key, StringCodec.INSTANCE).size();
    }

    /**
     * <p>
     * 通过key给field设置指定的值,如果key不存在,则先创建
     * </p>
     *
     * @param key
     * @param field 字段
     * @param value
     * @return 如果存在返回0 异常返回null
     */
    public Long hset(String key, String field, String value) {
        try {
            redissonClient.getMap(key, StringCodec.INSTANCE).put(field, value);
        } catch (Exception e) {
            return null;
        }
        return 0L;
    }

    /**
     * <p>
     * 通过key给field设置指定的值,如果key不存在则先创建,如果field已经存在,返回0
     * </p>
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Long hsetnx(String key, String field, String value) {
        return redissonClient.getMap(key, StringCodec.INSTANCE).fastPutIfAbsent(field, value) ? 0L : null;
    }


    /**
     * <p>
     * 通过key 和 field 获取指定的 value
     * </p>
     *
     * @param key
     * @param field
     * @return 没有返回null
     */
    public String hget(String key, String field) {
        return redissonClient.<String, String>getMap(key, StringCodec.INSTANCE).get(field);
    }


    /**
     * <p>
     * 通过key给指定的field的value加上给定的值
     * </p>
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    public Integer hincrby(String key, String field, Integer value) {
        return redissonClient.<String, Integer>getMap(key, StringCodec.INSTANCE).addAndGet(field, value);
    }

    /**
     * <p>
     * 通过key和field判断是否有指定的value存在
     * </p>
     *
     * @param key
     * @param field
     * @return
     */
    public Boolean hexists(String key, String field) {
        return redissonClient.<String, Object>getMap(key, StringCodec.INSTANCE).containsKey(field);
    }

    /**
     * <p>
     * 通过key返回field的数量
     * </p>
     *
     * @param key
     * @return
     */
    public Integer hlen(String key) {
        return new Integer(redissonClient.getMap(key, StringCodec.INSTANCE).size());

    }

    /**
     * <p>
     * 通过key 删除指定的 field
     * </p>
     *
     * @param key
     * @param fields 可以是 一个 field 也可以是 一个数组
     * @return
     */
    public Long hdel(String key, List<String> fields) {
        return redissonClient.getMap(key, StringCodec.INSTANCE).fastRemove(fields.toArray());
    }

    /**
     * <p>
     * 通过key返回所有的field
     * </p>
     *
     * @param key
     * @return
     */
    public Set<String> hkeys(String key) {
        return redissonClient.<String, Object>getMap(key, StringCodec.INSTANCE).keySet();
    }

    /**
     * <p>
     * 通过key返回所有和key有关的value
     * </p>
     *
     * @param key
     * @return
     */
    public List<String> hvals(String key) {
        List<String> ret = new ArrayList<>();
        ret.addAll(redissonClient.<String, String>getMap(key, StringCodec.INSTANCE).values());
        return ret;
    }

    /**
     * <p>
     * 通过key向list尾部添加字符串
     * </p>
     *
     * @param key
     * @param strs 可以使一个string 也可以使string数组
     * @return 返回list的value个数
     */
    public Integer rpush(String key, String... strs) {
        RList<Object> list = redissonClient.getList(key, StringCodec.INSTANCE);
        list.addAll(Arrays.asList(strs));
        return new Integer(list.size());
    }

    /**
     * <p>
     * 通过key向list尾部添加字符串
     * </p>
     *
     * @param key
     * @param strs
     * @return 返回list的value个数
     */
    public Integer rpushByList(String key, List<String> strs) {
        RList<Object> list = redissonClient.getList(key, StringCodec.INSTANCE);
        list.addAll(strs);
        return new Integer(list.size());
    }

    /**
     *  通过key向list尾部添加字符串
     * @param key redis - key
     * @param value value
     */
    public void rpushDeque(String key , String value ){
        redissonClient.getQueue(key, StringCodec.INSTANCE).offer(value);
    }
    /**
     * <p>
     * 通过key设置list指定下标位置的value
     * </p>
     * <p>
     * 如果下标超过list里面value的个数则报错
     * </p>
     *
     * @param key
     * @param index 从0开始
     * @param value
     * @return 成功返回OK
     */
    public String lset(String key, Integer index, String value) {
        RList<Object> list = redissonClient.getList(key, StringCodec.INSTANCE);
        list.set(index.intValue(), value);
        return "OK";
    }

    /**
     * <p>
     * 通过key从对应的list中删除指定的count个 和 value相同的元素
     * </p>
     *
     * @param key
     * @param count 当count为0时删除全部
     * @param value
     * @return 返回被删除的个数
     */
    public Integer lrem(String key, Integer count, String value) {
        List<String> list = redissonClient.getList(key, StringCodec.INSTANCE);
        Integer delCnt = 0;
        while (count-- > 0 && list.contains(value)) {
            list.remove(value);
            delCnt++;
        }
        return delCnt;
    }

    /**
     * <p>
     * 通过key保留list中从strat下标开始到end下标结束的value值
     * </p>
     *
     * @param key
     * @param start
     * @param end
     * @return 成功返回OK
     */
    public String ltrim(String key, int start, int end) {
        RList<String> list = redissonClient.getList(key, StringCodec.INSTANCE);
        list.trim(start, end);
        return "OK";
    }

    /**
     * <p>
     * 通过key保留list
     * @param key
     * @return list
     */
    public List<String> lList(String key, int start, int end) {
        RList<String> list = redissonClient.getList(key, StringCodec.INSTANCE);
        return list.range(start,end);
    }

    /**
     * <p>
     * 通过key从list的头部删除一个value,并返回该value
     * </p>
     *
     * @param key
     * @return
     */
    public String lpop(String key) {
        return redissonClient.<String>getQueue(key, StringCodec.INSTANCE).poll();
        // return redissonClient.<String>getDeque(key, StringCodec.INSTANCE).pollFirst();
    }

    /**
     * <p>
     * 通过key获取list中指定下标位置的value
     * </p>
     *
     * @param key
     * @param index
     * @return 如果没有返回null
     */
    public String lindex(String key, int index) {
        return redissonClient.<String>getList(key, StringCodec.INSTANCE).get(index);
    }

    /**
     * <p>
     * 通过key返回list的长度
     * </p>
     *
     * @param key
     * @return
     */
    public int llen(String key) {
        return redissonClient.<String>getList(key, StringCodec.INSTANCE).size();
    }

    /**
     * <p>
     * 将列表 key 下标为 index 的元素的值设置为 value
     * </p>
     *
     * @param key
     * @param index
     * @param value
     * @return 操作成功返回 ok ，否则返回错误信息
     */
    public String lset(String key, int index, String value) {
        return redissonClient.<String>getList(key, StringCodec.INSTANCE).set(index, value);
    }


    /**
     * <p>
     * 通过key向指定的set中添加value
     * </p>
     *
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 添加成功的个数
     */
    public int sadd(String key, String... members) {
        return redissonClient.getSet(key, StringCodec.INSTANCE).addAll(Arrays.asList(members)) ? members.length : 0;
    }

    /**
     * <p>
     * 通过key删除set中对应的value值
     * </p>
     *
     * @param key
     * @param members 可以是一个String 也可以是一个String数组
     * @return 删除的个数
     */
    public int srem(String key, String... members) {
        return redissonClient.getSet(key, StringCodec.INSTANCE).removeAll(Arrays.asList(members)) ? members.length : 0;
    }

    /**
     * <p>
     * 通过key随机删除一个set中的value并返回该值
     * </p>
     *
     * @param key
     * @return
     */
    public String spop(String key) {
        return redissonClient.<String>getSet(key, StringCodec.INSTANCE).removeRandom();
    }

    /**
     * <p>
     * 通过key获取set中的差集并存入到另一个key中
     * </p>
     * <p>
     * 以第一个set为标准
     * </p>
     *
     * @param dstkey 差集存入的key
     * @param keys   可以使一个string 则返回set中所有的value 也可以是string数组
     * @return
     */
    public int sdiffstore(String dstkey, String... keys) {
        return redissonClient.getSet(dstkey, StringCodec.INSTANCE).diff(keys);
    }

    /**
     * <p>
     * 通过key获取指定set中的交集 并将结果存入新的set中
     * </p>
     *
     * @param dstkey
     * @param keys   可以使一个string 也可以是一个string数组
     * @return
     */
    public int sinterstore(String dstkey, String... keys) {
        return redissonClient.getSet(dstkey, StringCodec.INSTANCE).intersection(keys);
    }

    /**
     * <p>
     * 通过key获取set中value的个数
     * </p>
     *
     * @param key
     * @return
     */
    public int scard(String key) {
        return redissonClient.getSet(key, StringCodec.INSTANCE).size();
    }

    /**
     * <p>
     * 通过key判断value是否是set中的元素
     * </p>
     *
     * @param key
     * @param member
     * @return
     */
    public Boolean sismember(String key, String member) {
        return redissonClient.<String>getSet(key, StringCodec.INSTANCE).contains(member);
    }

    /**
     * <p>
     * 通过key获取set中随机的value,不删除元素
     * </p>
     *
     * @param key
     * @return
     */
    public String srandmember(String key) {
        return redissonClient.<String>getSet(key, StringCodec.INSTANCE).random();
    }

    /**
     * <p>
     * 通过key获取set中所有的value
     * </p>
     *
     * @param key
     * @return
     */
    public Set<String> smembers(String key) {
        return redissonClient.getSet(key, StringCodec.INSTANCE);
    }

    /**
     * <p>
     * 为哈希表 key 中的域 field 的值加上增量 increment 。增量也可以为负数，相当于对给定域进行减法操作。 如果 key
     * 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。如果域 field 不存在，那么在执行命令前，域的值被初始化为 0 。
     * 对一个储存字符串值的域 field 执行 HINCRBY 命令将造成一个错误。本操作的值被限制在 64 位(bit)有符号数字表示之内。
     * </p>
     * <p>
     * 将名称为key的hash中field的value增加integer
     * </p>
     *
     * @param key
     * @param value
     * @param increment
     * @return 执行 HINCRBY 命令之后，哈希表 key 中域 field的值。异常返回0
     */
    public Long hincrBy(String key, String value, Long increment) {
        return redissonClient.<String, Long>getMap(key, StringCodec.INSTANCE).addAndGet(value, increment);
    }

    /**
     * <p>
     * 返回满足pattern表达式的所有key
     * </p>
     * <p>
     * keys(*)
     * </p>
     * <p>
     * 返回所有的key
     * </p>
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        Set<String> ret = new HashSet<>();
        redissonClient.getKeys().getKeysByPattern(pattern).forEach(key -> ret.add(key));
        return ret;
    }

    /**
     * <p>
     * 通过key判断值得类型
     * </p>
     *
     * @param key
     * @return
     */
    public String type(String key) {
        return redissonClient.getKeys().getType(key).name();
    }


    /**
     * @param listKey
     * @param setKey
     * @param setValue
     * @param expireTime
     * @Description 将业务数据写入缓存
     * 1. listKey-setKey (list)
     * 2 setKey-setValue; (key-value)
     * @Method setCacheListPush
     * @Author hyzhang
     * @Exception
     * @Date 2020/11/24 16:08
     */
    public void setCacheListPush(String listKey, String setKey, String setValue, int expireTime) {
        redissonClient.getList(listKey, StringCodec.INSTANCE).add(setKey);
        if (expireTime >= 0) {
            redissonClient.getBucket(setKey, StringCodec.INSTANCE).set(setValue, expireTime, TimeUnit.SECONDS);
        } else {
            redissonClient.getBucket(setKey, StringCodec.INSTANCE).set(setValue);
        }
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            jedis.rpush(listKey,setKey);
//            jedis.setex(setKey,expireTime == 0 ? -1:expireTime,setValue);
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        } finally {
//            returnResource(jedisPool, jedis);
//        }
    }

    /**
     * @param listKey
     * @Description 将业务数据移除缓存
     * 1. 根据listKey 获取 setKey集合
     * 2 调用redisUtil 删除缓存 setKey
     * 3 调用redisUtil 删除缓存list listKey
     * @Method setCacheListPush
     * @Author hyzhang
     * @Exception
     * @Date 2020/11/24 16:08
     */
    public void removeCacheListPush(String listKey) {
        redissonClient.<String>getList(listKey, StringCodec.INSTANCE).forEach(key -> del(key));
        del(listKey);
    }

    /**
     * 序列化对象
     *
     * @param obj
     * @return 对象需实现Serializable接口
     */
    public static byte[] objToSerialize(Object obj) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream byteOut = null;
        try {
            byteOut = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(byteOut);
            oos.writeObject(obj);
            byte[] bytes = byteOut.toByteArray();
            return bytes;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 反序列化对象
     *
     * @param bytes
     * @return 对象需实现Serializable接口
     */
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            //反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        }
        return null;
    }
}