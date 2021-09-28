import com.bjpowernode.crm.mapper.TypeMapper;
import com.bjpowernode.crm.mapper.UserMapper;
import com.bjpowernode.crm.mapper.ValueMapper;
import com.bjpowernode.crm.pojo.Type;
import com.bjpowernode.crm.pojo.User;
import com.bjpowernode.crm.pojo.Value;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration("classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class Tester {
    @Autowired
    private TypeMapper typeMapper;

    @Autowired
    private ValueMapper valueMapper;

    @Test
    public void test01() throws JsonProcessingException {
        Value value = new Value();
        value.setId(UUIDUtil.getUUID());
        value.setTid("color");
        value.setValue("red");
        value.setText("红");
        valueMapper.save(value);
    }

    @Test
    public void test02() {
        Value value = new Value();
        value.setId("4ae597319b334d85bddcc04d5ff8c676");
        value.setTid("color");
        value.setValue("red");
        value.setText("红");
        value.setOrderNo("1");
        valueMapper.update(value);
    }
}
