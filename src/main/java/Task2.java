import org.apache.commons.codec.digest.DigestUtils;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Task2 {

    public static final String E_MAIL = "egorovcf@gmail.com";

    public static void main(String[] args) {
        // список всех файлов
        File dir = new File("data");
        List<File> fileList = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.isFile())
                fileList.add(file);
        }

        // составим список хэш-строк
        List<String> hashStings = new LinkedList<>();
        for (int i = 0; i < fileList.size(); i++) {
            try (FileInputStream fis = new FileInputStream("data/" + fileList.get(i).getName())) {
                byte[] buffer = fis.readAllBytes();
                String sha3Hex = new DigestUtils("SHA3-256").digestAsHex(buffer);
                hashStings.add(sha3Hex);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        // Отсортируем строки по возрастанию
        Collections.sort(hashStings);

        // Склеим все строки
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < hashStings.size(); i++) {
            stringBuilder.append(hashStings.get(i));
        }

        // добавим емеил и найдем SHA3-256 от полученной строки
        stringBuilder.append(E_MAIL);
        String result = new DigestUtils("SHA3-256").digestAsHex(stringBuilder.toString().getBytes());
        System.out.println(result);
    }
}