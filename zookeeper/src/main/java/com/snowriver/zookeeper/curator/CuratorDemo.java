package com.snowriver.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class CuratorDemo {

    private static String CONNECTION_STR="192.168.153.128:2181,192.168.153.129:2181,192.168.153.131:2181";

    public static void main(String[] args) throws Exception {
//        CuratorFramework curatorFramework= CuratorFrameworkFactory.newClient("")

        CuratorFramework curatorFramework= CuratorFrameworkFactory.builder().
                connectString(CONNECTION_STR).sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(3000,3)).build();
        //ExponentialBackoffRetry
        //RetryOneTime  仅仅只重试一次
        //RetryUntilElapsed
        //RetryNTimes

        curatorFramework.start(); //启动
        //createData(curatorFramework);
//        updateData(curatorFramework);
        deleteData(curatorFramework);
        //CRUD
//        curatorFramework.create();
//        curatorFramework.setData(); //修改
//        curatorFramework.delete() ;// 删除
//        curatorFramework.getData(); //查询
    }

    private static void createData(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).
                forPath("/snowriver","test".getBytes());

    }

    private static void updateData(CuratorFramework curatorFramework) throws Exception {
        curatorFramework.setData().forPath("/snowriver","up".getBytes());

    }

    private static void deleteData(CuratorFramework curatorFramework) throws Exception {
        Stat stat=new Stat();
        String value=new String(curatorFramework.getData().storingStatIn(stat).forPath("/snowriver"));
        curatorFramework.delete().withVersion(stat.getVersion()).forPath("/snowriver");
    }

}