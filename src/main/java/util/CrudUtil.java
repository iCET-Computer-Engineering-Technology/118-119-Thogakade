package util;

import db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String sql,Object...args) throws SQLException {
        PreparedStatement psTm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        for(int i=0;i<args.length;i++){
            psTm.setObject((i+1),args[i]);
        }

        if(sql.startsWith("SELECT")||sql.startsWith("select")){
            return (T) psTm.executeQuery();
        }

        return (T) (Boolean) (psTm.executeUpdate()>0);

    }


//    executeupdate - insert,update delete ,
//    executeQuary - select


//    public static void main(String[] args) {
//        Integer sum1 = getSum(10);
//        Integer sum2 = getSum();
//        Integer sum3 = getSum(10, 20, 30);
//        Integer sum4 = getSum(10, 20, 30, 40, 50, 60, 70);
//        Integer sum5 = getSum(10, 20, 30, 40, 50, 60, 70, 80, 90, 100);
//
//        System.out.println(sum1);
//        System.out.println(sum2);
//        System.out.println(sum3);
//        System.out.println(sum4);
//        System.out.println(sum5);
//    }
//
//
//    public static Integer getSum(Integer... args){
//        int sum = 0;
//
//        for (int i = 0; i < args.length; i++) {
//            sum += args[i];
//        }
//        return sum;
//
//    }
}
