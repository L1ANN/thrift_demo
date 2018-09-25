package yjmzz.thrift.study;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import yjmyzz.thrift.study.service.DemoService;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 下午3:43 2018/9/22
 * @Modified By:
 */
public class ThriftServer {
    public static DemoService.Iface service;

    public static DemoService.Processor processor;

    public static void main(String[] args) {
        try {
            service = new DemoServiceImpl();
            processor = new DemoService.Processor(service);

            Runnable simple = new Runnable() {
                public void run() {
                    simple(processor);
                }
            };
            new Thread(simple).start();

        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void simple(DemoService.Processor processor) {
        try {
            /**
             * 创建transport
             */
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer.Args tArgs = new TServer.Args(serverTransport);

            /**
             * 为transport创建Protocol
             */
            tArgs.protocolFactory(new TBinaryProtocol.Factory());

            /**
             * 为Protocol创建processor
             */
            tArgs.processor(processor);

            /**
             * 创建server并启动
             * TSimpleServer简单的单线程服务模型，一般用于测试
             */
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
