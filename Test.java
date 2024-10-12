import hotel.Rooms;
import java.io.BufferedReader; // Các thư viện cần có để làm việc với file
import java.io.FileReader; // Thư viện này để đọc
import java.io.IOException; // In ra các thông báo lỗi
import java.io.Writer;
import java.util.ArrayList;
import java.io.FileWriter;
import java.util.Scanner;


@SuppressWarnings("unused")
public class Test{
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Rooms.main(args);
    }
}