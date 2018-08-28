package utils;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;
import org.loader.litepal.Person;
import java.util.List;

public class SqlUtils {

    // 插入数据
    public static void insertData(String name, String time, String age, String sex) {
        Person p = new Person();
        p.setName(name);
        p.setTime(time);
        p.setAge(age);
        p.setSex(sex);
        p.save();

    }
    //删除当前时间的数据
    public static void deletePersonTime(String time) {
        DataSupport.deleteAll(Person.class,"time=?",time);
    }

    // 删除所有的数据，
    public static void deleteDataAll() {
        DataSupport.deleteAll(Person.class);
    }

    // 遍历查询所有数据,保存到List里面
    public static void lookupDataAll(List<Person>list) {
        list.clear();
        List<Person> persons = DataSupport.findAll(Person.class);
        for (Person p : persons) {
            System.out.println(p.getName());
            Log.e("SqlUtils", "p.getName()= " + p.getName() + "p.getTime()= " + p.getTime() + "p.getAge()= " + p.getAge() + "p.getSex()= " + p.getSex());
            list.add(p);
        }
    }

    //根据时间查询对象
    public static Person timeGetlookupDataAll(String time) {
        List<Person> persons = DataSupport.findAll(Person.class);
        for (Person p : persons) {
            System.out.println(p.getName());
            if (time.equals(p.getTime())) {
                return p;
            }
        }
        return null;
    }

    //根据时间改变里面的年龄，可以灵活的更换根据数据更新内容
    public static void updataSql(String time,String age) {
        SQLiteDatabase db = Connector.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("age", age);
        db.update("Person", values, "time = ?", new String[] {time});
    }


    // 除此之外litepal还给我们提供了一个可以“自由发挥”的方法，也就是自己书写sql语句
    public static void sqllite() {
        Cursor cursor = DataSupport.findBySQL("select * from phone where id=?",
                "3");
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            System.out.println(cursor.getString(cursor
                    .getColumnIndex("phonenumber")));
        }
        cursor.close();
    }
}
