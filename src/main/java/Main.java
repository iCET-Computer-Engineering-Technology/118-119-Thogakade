import org.jasypt.util.text.BasicTextEncryptor;

public class Main {
    public static void main(String[] args) {
//        Starter.main(args);

//        sample@1234

//        =jkhkjSLKJL564655dffgdfg1346545kj3j3n33h == sample@1234

        String key = "1234";

        String myPassword="Sample@1212";

        BasicTextEncryptor basicTextEncryptor = new BasicTextEncryptor();
        basicTextEncryptor.setPassword(key);

        String encrypt = basicTextEncryptor.encrypt(myPassword);

        System.out.println("encrypt value : "+encrypt);

        String decrypt = basicTextEncryptor.decrypt(encrypt);

//        String decrypt = basicTextEncryptor.decrypt("0v9UzNE71brNAvoIXoDKu1B5uKNHKsxX");

        System.out.println("decrypt value : "+ decrypt);

/*

<!-- Source: https://mvnrepository.com/artifact/org.jasypt/jasypt -->
<dependency>
    <groupId>org.jasypt</groupId>
    <artifactId>jasypt</artifactId>
    <version>1.9.3</version>
    <scope>compile</scope>
</dependency>
 */


    }
}