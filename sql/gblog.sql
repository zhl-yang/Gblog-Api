/*
 Navicat Premium Data Transfer

 Source Server         : zhloong
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3306
 Source Schema         : gblog

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 19/07/2022 16:28:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog_article
-- ----------------------------
DROP TABLE IF EXISTS `blog_article`;
CREATE TABLE `blog_article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `nickname` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '作者昵称',
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章标题',
  `summary` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章摘要',
  `content` longtext COLLATE utf8mb4_unicode_ci COMMENT '文章内容txt',
  `content_html` longtext COLLATE utf8mb4_unicode_ci COMMENT '文章内容html',
  `view_num` int(11) DEFAULT NULL COMMENT '浏览数',
  `comment_num` int(11) DEFAULT NULL COMMENT '评论数',
  `weight` int(11) NOT NULL COMMENT '权重',
  `tags` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文章标签',
  `category_id` int(11) DEFAULT NULL COMMENT '文章分类ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `delete_time` int(11) NOT NULL DEFAULT '0' COMMENT '删除时间',
  `banner` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `key_userId` (`user_id`) USING BTREE,
  KEY `key_category_id` (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1051 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章表';

-- ----------------------------
-- Records of blog_article
-- ----------------------------
BEGIN;
INSERT INTO `blog_article` (`id`, `user_id`, `nickname`, `title`, `summary`, `content`, `content_html`, `view_num`, `comment_num`, `weight`, `tags`, `category_id`, `create_time`, `update_time`, `delete_time`, `banner`) VALUES (1013, 1, 'zhloong', 'Docker：docker的介绍和安装（一）', '什么是docker？\n\nDocker是通过内核虚拟化技术（namespaces及cgroups）来提供容器的资源隔离与资源限制。', '**什么是docker？**\n\nDocker是通过内核虚拟化技术（namespaces及cgroups）来提供容器的资源隔离与资源限制。由于Docker通过操作系统层的虚拟化实现隔离（对操作系统的内核有要求，centos6已经没办法安装最新版docker，至少需要centos7的系统，如果需要最新docker特性，需要使用unbantu，因为redhalt内核比unbantu内核版本低），所以Docker容器在运行时，不需要类似虚拟机（VM）额外的操作系统开销，从而比kvm虚拟机更轻量。 docker是一种软件的打包技术。\n\n**docker理念**\n\ndocker的主要目标是\"Build,Ship and Run any App,Angwhere\",构建，运输，处处运行 构建：制作docker镜像，打包容器的所有系统目录文件 运输：下载docker镜像 运行：基于docker镜像提供的rootfs，启动容器 总结：只要能运行docker容器，那么docker镜像中已经安装好的软件也可以运行，所以说docker是一种软件的打包技术，一次构建，出处运行。\n\n**docker的优点**\n\n1.解决了操作系统和软件运行环境的依赖\n\n2.对于开发人员来说，再也不用担心不会部署开发环境 \n\n3.开发环境，测试环境和生产环境高度一致。 \n\n4.让用户体验产品新特性的又一种思路。\n\n**docker的安装**\n\n1.  安装一台CentOS7（博主采用CentOS7.4）\n    \n        [root@docker01 /]# cat /etc/redhat-release\n        CentOS Linux release 7.4.1708 (Core) \n    \n2.  修改主机名为docker01\n    \n        hostnamectl set-hostname docker01\n    \n3.  配置yum源\n    \n        #删除本地源\n        rm -fr  /etc/yum.repos.d/local.repo\n        #下载阿里云源\n        curl -o /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo\n        #下载docker源\n        curl -o /etc/yum.repos.d/docker-ce.repo https://mirrors.ustc.edu.cn/docker-ce/linux/centos/docker-ce.repo\n        \n        #将源文件中的地址替换为清华源\n        sed -i \'s#download.docker.com#mirrors.tuna.tsinghua.edu.cn/docker-ce#g\' /etc/yum.repos.d/docker-ce.repo \n        \n    \n4.  安装docker\n    \n        yum install docker-ce -y\n    \n5.  启动docker，并置为开机启动\n    \n        [root@docker01 /]# systemctl start docker.service\n        [root@docker01 /]# systemctl enable docker.service\n        Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.\n        [root@docker01 /]# systemctl status docker.service\n        ● docker.service - Docker Application Container Engine\n           Loaded: loaded (/usr/lib/systemd/system/docker.service; enabled; vendor preset: disabled)\n           Active: active (running) since Mon 2019-07-22 10:34:27 CST; 21s ago\n             Docs: https://docs.docker.com\n         Main PID: 2602 (dockerd)\n           CGroup: /system.slice/docker.service\n                   └─2602 /usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock\n        \n        Jul 22 10:34:27 docker01 dockerd[2602]: time=\"2019-07-22T10:34:27.023394018+08:00\" level=info msg=\"pickfirstBalancer: HandleSubConnStateChange: 0xc4200452e0, REA...module=grpc\n        Jul 22 10:34:27 docker01 dockerd[2602]: time=\"2019-07-22T10:34:27.029328234+08:00\" level=info msg=\"pickfirstBalancer: HandleSubConnStateChange: 0xc4200450b0, REA...module=grpc\n        Jul 22 10:34:27 docker01 dockerd[2602]: time=\"2019-07-22T10:34:27.043464741+08:00\" level=info msg=\"Graph migration to content-addressability took 0.00 seconds\"\n        Jul 22 10:34:27 docker01 dockerd[2602]: time=\"2019-07-22T10:34:27.044024276+08:00\" level=info msg=\"Loading containers: start.\"\n        Jul 22 10:34:27 docker01 dockerd[2602]: time=\"2019-07-22T10:34:27.273802596+08:00\" level=info msg=\"Default bridge (docker0) is assigned with an IP address 172.17...IP address\"\n        Jul 22 10:34:27 docker01 dockerd[2602]: time=\"2019-07-22T10:34:27.334002476+08:00\" level=info msg=\"Loading containers: done.\"\n        Jul 22 10:34:27 docker01 dockerd[2602]: time=\"2019-07-22T10:34:27.355108588+08:00\" level=info msg=\"Docker daemon\" commit=0dd43dd graphdriver(s)=overlay2 version=18.09.8\n        Jul 22 10:34:27 docker01 dockerd[2602]: time=\"2019-07-22T10:34:27.355245821+08:00\" level=info msg=\"Daemon has completed initialization\"\n        Jul 22 10:34:27 docker01 dockerd[2602]: time=\"2019-07-22T10:34:27.399274108+08:00\" level=info msg=\"API listen on /var/run/docker.sock\"\n        Jul 22 10:34:27 docker01 systemd[1]: Started Docker Application Container Engine.\n        Hint: Some lines were ellipsized, use -l to show in full.\n    \n\n**docker架构**\n\n启动docker： systemctl start docker\n\n开机自启：systemctl enable docker\n\ndocker是一个cs架构： 通过docker version来查看\n\n    [root@docker01 /]# docker version\n    Client:\n     Version:           18.09.8\n     API version:       1.39\n     Go version:        go1.10.8\n     Git commit:        0dd43dd87f\n     Built:             Wed Jul 17 17:40:31 2019\n     OS/Arch:           linux/amd64\n     Experimental:      false\n    \n    Server: Docker Engine - Community\n     Engine:\n      Version:          18.09.8\n      API version:      1.39 (minimum version 1.12)\n      Go version:       go1.10.8\n      Git commit:       0dd43dd\n      Built:            Wed Jul 17 17:10:42 2019\n      OS/Arch:          linux/amd64\n      Experimental:     false\n\ndocker最重要的三大组件： 镜像，容器，仓库\n', '<p><strong>什么是docker？</strong></p>\n<p>Docker是通过内核虚拟化技术（namespaces及cgroups）来提供容器的资源隔离与资源限制。由于Docker通过操作系统层的虚拟化实现隔离（对操作系统的内核有要求，centos6已经没办法安装最新版docker，至少需要centos7的系统，如果需要最新docker特性，需要使用unbantu，因为redhalt内核比unbantu内核版本低），所以Docker容器在运行时，不需要类似虚拟机（VM）额外的操作系统开销，从而比kvm虚拟机更轻量。 docker是一种软件的打包技术。</p>\n<p><strong>docker理念</strong></p>\n<p>docker的主要目标是&quot;Build,Ship and Run any App,Angwhere&quot;,构建，运输，处处运行 构建：制作docker镜像，打包容器的所有系统目录文件 运输：下载docker镜像 运行：基于docker镜像提供的rootfs，启动容器 总结：只要能运行docker容器，那么docker镜像中已经安装好的软件也可以运行，所以说docker是一种软件的打包技术，一次构建，出处运行。</p>\n<p><strong>docker的优点</strong></p>\n<p>1.解决了操作系统和软件运行环境的依赖</p>\n<p>2.对于开发人员来说，再也不用担心不会部署开发环境</p>\n<p>3.开发环境，测试环境和生产环境高度一致。</p>\n<p>4.让用户体验产品新特性的又一种思路。</p>\n<p><strong>docker的安装</strong></p>\n<ol>\n<li>\n<p>安装一台CentOS7（博主采用CentOS7.4）</p>\n<pre><code>[root@docker01 /]# cat /etc/redhat-release\nCentOS Linux release 7.4.1708 (Core) \n</code></pre>\n</li>\n<li>\n<p>修改主机名为docker01</p>\n<pre><code>hostnamectl set-hostname docker01\n</code></pre>\n</li>\n<li>\n<p>配置yum源</p>\n<pre><code>#删除本地源\nrm -fr  /etc/yum.repos.d/local.repo\n#下载阿里云源\ncurl -o /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo\n#下载docker源\ncurl -o /etc/yum.repos.d/docker-ce.repo https://mirrors.ustc.edu.cn/docker-ce/linux/centos/docker-ce.repo\n\n#将源文件中的地址替换为清华源\nsed -i \'s#download.docker.com#mirrors.tuna.tsinghua.edu.cn/docker-ce#g\' /etc/yum.repos.d/docker-ce.repo \n</code></pre>\n</li>\n<li>\n<p>安装docker</p>\n<pre><code>yum install docker-ce -y\n</code></pre>\n</li>\n<li>\n<p>启动docker，并置为开机启动</p>\n<pre><code>[root@docker01 /]# systemctl start docker.service\n[root@docker01 /]# systemctl enable docker.service\nCreated symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.\n[root@docker01 /]# systemctl status docker.service\n● docker.service - Docker Application Container Engine\n   Loaded: loaded (/usr/lib/systemd/system/docker.service; enabled; vendor preset: disabled)\n   Active: active (running) since Mon 2019-07-22 10:34:27 CST; 21s ago\n     Docs: https://docs.docker.com\n Main PID: 2602 (dockerd)\n   CGroup: /system.slice/docker.service\n           └─2602 /usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock\n\nJul 22 10:34:27 docker01 dockerd[2602]: time=&quot;2019-07-22T10:34:27.023394018+08:00&quot; level=info msg=&quot;pickfirstBalancer: HandleSubConnStateChange: 0xc4200452e0, REA...module=grpc\nJul 22 10:34:27 docker01 dockerd[2602]: time=&quot;2019-07-22T10:34:27.029328234+08:00&quot; level=info msg=&quot;pickfirstBalancer: HandleSubConnStateChange: 0xc4200450b0, REA...module=grpc\nJul 22 10:34:27 docker01 dockerd[2602]: time=&quot;2019-07-22T10:34:27.043464741+08:00&quot; level=info msg=&quot;Graph migration to content-addressability took 0.00 seconds&quot;\nJul 22 10:34:27 docker01 dockerd[2602]: time=&quot;2019-07-22T10:34:27.044024276+08:00&quot; level=info msg=&quot;Loading containers: start.&quot;\nJul 22 10:34:27 docker01 dockerd[2602]: time=&quot;2019-07-22T10:34:27.273802596+08:00&quot; level=info msg=&quot;Default bridge (docker0) is assigned with an IP address 172.17...IP address&quot;\nJul 22 10:34:27 docker01 dockerd[2602]: time=&quot;2019-07-22T10:34:27.334002476+08:00&quot; level=info msg=&quot;Loading containers: done.&quot;\nJul 22 10:34:27 docker01 dockerd[2602]: time=&quot;2019-07-22T10:34:27.355108588+08:00&quot; level=info msg=&quot;Docker daemon&quot; commit=0dd43dd graphdriver(s)=overlay2 version=18.09.8\nJul 22 10:34:27 docker01 dockerd[2602]: time=&quot;2019-07-22T10:34:27.355245821+08:00&quot; level=info msg=&quot;Daemon has completed initialization&quot;\nJul 22 10:34:27 docker01 dockerd[2602]: time=&quot;2019-07-22T10:34:27.399274108+08:00&quot; level=info msg=&quot;API listen on /var/run/docker.sock&quot;\nJul 22 10:34:27 docker01 systemd[1]: Started Docker Application Container Engine.\nHint: Some lines were ellipsized, use -l to show in full.\n</code></pre>\n</li>\n</ol>\n<p><strong>docker架构</strong></p>\n<p>启动docker： systemctl start docker</p>\n<p>开机自启：systemctl enable docker</p>\n<p>docker是一个cs架构： 通过docker version来查看</p>\n<pre><code>[root@docker01 /]# docker version\nClient:\n Version:           18.09.8\n API version:       1.39\n Go version:        go1.10.8\n Git commit:        0dd43dd87f\n Built:             Wed Jul 17 17:40:31 2019\n OS/Arch:           linux/amd64\n Experimental:      false\n\nServer: Docker Engine - Community\n Engine:\n  Version:          18.09.8\n  API version:      1.39 (minimum version 1.12)\n  Go version:       go1.10.8\n  Git commit:       0dd43dd\n  Built:            Wed Jul 17 17:10:42 2019\n  OS/Arch:          linux/amd64\n  Experimental:     false\n</code></pre>\n<p>docker最重要的三大组件： 镜像，容器，仓库</p>\n', 18, 0, 0, '9', 5, '2022-01-26 09:53:55', '2022-01-26 09:53:55', 0, 'http://p5.qhimg.com/bdm/1600_900_85/t013ae9ec9d0ef1eceb.jpg');
COMMIT;

-- ----------------------------
-- Table structure for blog_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_article_tag`;
CREATE TABLE `blog_article_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID',
  `tag_id` int(11) NOT NULL COMMENT '标签ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `key_tagId` (`tag_id`) USING BTREE,
  KEY `key_articleId` (`article_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1549227468081127426 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章标签表';

-- ----------------------------
-- Records of blog_article_tag
-- ----------------------------
BEGIN;
INSERT INTO `blog_article_tag` (`id`, `article_id`, `tag_id`, `create_time`, `update_time`) VALUES (1070, 1013, 9, '2022-01-26 09:53:55', '2022-01-26 09:53:55');
COMMIT;

-- ----------------------------
-- Table structure for blog_category
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `category_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类别名字',
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类别图标',
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '分类描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文章类别表';

-- ----------------------------
-- Records of blog_category
-- ----------------------------
BEGIN;
INSERT INTO `blog_category` (`id`, `category_name`, `avatar`, `description`, `create_time`) VALUES (1, '前端', '/category/front.png', NULL, '2018-07-06 14:50:52');
INSERT INTO `blog_category` (`id`, `category_name`, `avatar`, `description`, `create_time`) VALUES (2, '后端', '/category/back.png', NULL, '2018-07-06 14:50:52');
INSERT INTO `blog_category` (`id`, `category_name`, `avatar`, `description`, `create_time`) VALUES (3, '生活', '/category/lift.jpg', NULL, '2018-07-06 14:50:52');
INSERT INTO `blog_category` (`id`, `category_name`, `avatar`, `description`, `create_time`) VALUES (4, '数据库', '/category/database.png', NULL, '2018-07-06 14:50:52');
INSERT INTO `blog_category` (`id`, `category_name`, `avatar`, `description`, `create_time`) VALUES (5, '编程语言', '/category/language.png', NULL, '2018-07-06 14:50:52');
INSERT INTO `blog_category` (`id`, `category_name`, `avatar`, `description`, `create_time`) VALUES (6, '服务器', '/category/language.png', NULL, '2022-07-18 17:29:13');
COMMIT;

-- ----------------------------
-- Table structure for blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `blog_comment`;
CREATE TABLE `blog_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `article_id` bigint(20) DEFAULT NULL COMMENT '文章ID',
  `content` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评论内容',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父评论Id',
  `to_uid` bigint(20) DEFAULT NULL COMMENT '评论的评论用户ID',
  `level_flag` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT '0' COMMENT '评论级别',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `key_articleId` (`article_id`) USING BTREE,
  KEY `key_userId` (`user_id`) USING BTREE,
  KEY `key_parentId` (`parent_id`) USING BTREE,
  KEY `key_toUserId` (`to_uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';

-- ----------------------------
-- Records of blog_comment
-- ----------------------------
BEGIN;
INSERT INTO `blog_comment` (`id`, `user_id`, `article_id`, `content`, `parent_id`, `to_uid`, `level_flag`, `create_time`, `update_time`) VALUES (104, 1, 1013, 'debugger', NULL, NULL, '0', '2022-01-22 15:25:17', '2022-01-22 15:25:17');
INSERT INTO `blog_comment` (`id`, `user_id`, `article_id`, `content`, `parent_id`, `to_uid`, `level_flag`, `create_time`, `update_time`) VALUES (105, 1, 1013, '膜拜大佬', NULL, NULL, '0', '2022-01-24 11:01:11', '2022-01-24 11:01:11');
INSERT INTO `blog_comment` (`id`, `user_id`, `article_id`, `content`, `parent_id`, `to_uid`, `level_flag`, `create_time`, `update_time`) VALUES (111, 1, 1013, '不错', NULL, NULL, '0', '2022-01-25 18:10:12', '2022-01-25 18:10:12');
INSERT INTO `blog_comment` (`id`, `user_id`, `article_id`, `content`, `parent_id`, `to_uid`, `level_flag`, `create_time`, `update_time`) VALUES (112, 1, 1013, '不错，和我罗老师有的一拼', NULL, NULL, '0', '2022-03-08 17:01:11', '2022-03-08 17:01:11');
COMMIT;

-- ----------------------------
-- Table structure for blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `blog_tag`;
CREATE TABLE `blog_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tag_name` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签名字',
  `avatar` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签图标',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标签表';

-- ----------------------------
-- Records of blog_tag
-- ----------------------------
BEGIN;
INSERT INTO `blog_tag` (`id`, `tag_name`, `avatar`, `create_time`) VALUES (1, 'Java', '/tag/java.png', '2018-07-06 14:40:41');
INSERT INTO `blog_tag` (`id`, `tag_name`, `avatar`, `create_time`) VALUES (2, 'Spring', '/tag/spring.svg', '2018-07-06 14:46:55');
INSERT INTO `blog_tag` (`id`, `tag_name`, `avatar`, `create_time`) VALUES (3, 'Hibernate', '/tag/hibernate.svg', '2018-07-06 14:46:55');
INSERT INTO `blog_tag` (`id`, `tag_name`, `avatar`, `create_time`) VALUES (4, 'Maven', '/tag/maven.png', '2018-07-06 14:46:55');
INSERT INTO `blog_tag` (`id`, `tag_name`, `avatar`, `create_time`) VALUES (5, 'Html', '/tag/html.png', '2018-07-06 14:46:55');
INSERT INTO `blog_tag` (`id`, `tag_name`, `avatar`, `create_time`) VALUES (6, 'JavaScript', '/tag/js.png', '2018-07-06 14:46:55');
INSERT INTO `blog_tag` (`id`, `tag_name`, `avatar`, `create_time`) VALUES (7, 'Vue', '/tag/vue.png', '2018-07-06 14:46:55');
INSERT INTO `blog_tag` (`id`, `tag_name`, `avatar`, `create_time`) VALUES (8, 'Css', '/tag/css.png', '2018-07-06 14:46:55');
INSERT INTO `blog_tag` (`id`, `tag_name`, `avatar`, `create_time`) VALUES (9, 'Docker', '/tag/css.png', '2022-01-24 16:37:44');
INSERT INTO `blog_tag` (`id`, `tag_name`, `avatar`, `create_time`) VALUES (10, 'GIt', '/tag/css.png', '2022-01-24 16:41:05');
INSERT INTO `blog_tag` (`id`, `tag_name`, `avatar`, `create_time`) VALUES (11, 'Linux', '/tag/css.png', '2022-07-18 17:28:34');
COMMIT;

-- ----------------------------
-- Table structure for gbolg_friend
-- ----------------------------
DROP TABLE IF EXISTS `gbolg_friend`;
CREATE TABLE `gbolg_friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `domain` varchar(1024) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `type` char(1) DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gbolg_friend
-- ----------------------------
BEGIN;
INSERT INTO `gbolg_friend` (`id`, `name`, `email`, `domain`, `content`, `type`) VALUES (1, '网站源码', 'bbb@cc.com', 'https://gitee.com/zhloong/Gblog-zhloong', '网站源码', '0');
INSERT INTO `gbolg_friend` (`id`, `name`, `email`, `domain`, `content`, `type`) VALUES (6, '随机壁纸', 'boss@zhloong.xyz', 'https://admin.zhloong.xyz/#/wallpaper', '随机返回一张自定义风格的壁纸（页面双击可配置）', '0');
INSERT INTO `gbolg_friend` (`id`, `name`, `email`, `domain`, `content`, `type`) VALUES (7, 'ahangのblog', '001@ahangyu.cc', 'http://ahangyu.cc', ' ahangのblog', '0');
INSERT INTO `gbolg_friend` (`id`, `name`, `email`, `domain`, `content`, `type`) VALUES (8, '电脑壁纸', 'bbb@cc.com', 'https://bizhi.vercel.app', '来自必应、360的壁纸', '0');
COMMIT;

-- ----------------------------
-- Table structure for operation_log
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ip` varchar(255) DEFAULT NULL,
  `browser_name` varchar(255) DEFAULT NULL,
  `browser_version` varchar(255) DEFAULT NULL,
  `browser_system` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `param` text,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8224 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of operation_log
-- ----------------------------
BEGIN;
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8206, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/friend', NULL, '2022-07-19 16:22:07', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8207, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/tags', NULL, '2022-07-19 16:22:07', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8208, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/category', NULL, '2022-07-19 16:22:07', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8209, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/post/list', NULL, '2022-07-19 16:22:07', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8210, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/social', NULL, '2022-07-19 16:22:07', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8211, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/site', NULL, '2022-07-19 16:22:07', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8212, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/focus/list', NULL, '2022-07-19 16:22:07', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8213, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/site', NULL, '2022-07-19 16:22:07', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8214, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/social', NULL, '2022-07-19 16:22:07', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8215, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/social', NULL, '2022-07-19 16:22:18', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8216, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/friend', NULL, '2022-07-19 16:22:18', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8217, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/category', NULL, '2022-07-19 16:22:18', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8218, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/tags', NULL, '2022-07-19 16:22:18', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8219, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/site', NULL, '2022-07-19 16:22:18', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8220, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/post/list', NULL, '2022-07-19 16:22:18', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8221, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/focus/list', NULL, '2022-07-19 16:22:18', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8222, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/social', NULL, '2022-07-19 16:22:18', '{}');
INSERT INTO `operation_log` (`id`, `ip`, `browser_name`, `browser_version`, `browser_system`, `url`, `user_id`, `create_time`, `param`) VALUES (8223, '0:0:0:0:0:0:0:1', 'Chrome 10', '103.0.0.0', 'Mac OS X', '/site', NULL, '2022-07-19 16:22:18', '{}');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
