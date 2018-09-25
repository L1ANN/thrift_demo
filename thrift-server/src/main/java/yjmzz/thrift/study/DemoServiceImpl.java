package yjmzz.thrift.study;

import org.apache.thrift.TException;
import yjmyzz.thrift.study.dto.Person;
import yjmyzz.thrift.study.dto.QueryParameter;
import yjmyzz.thrift.study.service.DemoService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:L1ANN
 * @Description:
 * @Date:Created in 下午3:42 2018/9/22
 * @Modified By:
 */
public class DemoServiceImpl implements DemoService.Iface{

    public String ping() throws TException {
        System.out.println("ping()");
        return "pong";
    }

    public List<Person> getPersonList(QueryParameter parameter) throws TException {
        //System.out.println(parameter.getAgeStart() + " - " + parameter.getAgeEnd());

        List<Person> list = new ArrayList<Person>(10);
        for (short i = 0; i < 10; i++) {
            Person p = new Person();
            p.setAge(i);
            p.setChildrenCount(Byte.valueOf(i + ""));
            p.setName("test" + i);
            p.setSalary(10000D);
            p.setSex(true);
            list.add(p);
        }
        return list;
    }
}
