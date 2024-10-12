import java.io.BufferedReader; // Các thư viện cần có để làm việc với file
import java.io.FileReader; // Thư viện này để đọc
import java.io.FileWriter; // Thư viện này để ghi
import java.io.IOException; // In ra các thông báo lỗi
import java.util.Scanner;
    /*
     * - Class phòng: 
        + Số phòng
        + Loại phòng
        + Giá phòng
        + Trạng thái phòng
        + Số lượng phòng trống
     */
class rooms{
    Scanner scanner = new Scanner(System.in);
    public static String[] roomNumber = new String[100];
    public static String[] type = new String[100];
    public static String[] roomsCost = new String[100];
    public static String[] status = new String[100];
    static int i=0;
    @SuppressWarnings("unused")
    private int[] Room_unUsed;

    public static void CheckFileRoom(){
        String file = "Room.csv"; // Biến lưu trữ đường dẫn đến file
        String line ="";
        String delimiter =",";
        try(BufferedReader br = new BufferedReader( new FileReader(file))){
            // BufferedReader Dùng cái này để đọc từng dòng
            while((line = br.readLine()) !=null){
                // Kiểm tra nếu dòng tồn tại thì sẽ được lưu vào biến line
                String[] values = line.split(delimiter); // Tách từng giá trị trong một dòng ra
                roomNumber[i] = values[0]; // Lưu số phòng vào mảng
                type[i] = values[1]; // Lưu loại phòng vào mảng
                roomsCost[i] = values[2]; // Lưu giá phòng vào mảng
                status[i] = values[3]; // Lưu trạng thái phòng vào mảng
                i+=1; // Tăng biến đếm phần tử lên
            }
        } catch (IOException e) {
            System.out.println("Lỗi tìm file! "+ e.getMessage());
            // e.printStackTrace();
        }
    }
    public void GhiFile(){
        String file = "Room.csv"; // Biến lưu trữ đường dẫn đến file
        System.out.println("Nhap so luong phong muon them");
        int n = scanner.nextInt();
        for(int j=0;j<n;j++){
            System.out.println("Nhap so phong: ");
            roomNumber[i] = scanner.next();
            System.out.println("Nhap loai phong: ");
            type[i] = scanner.next();
            System.out.println("Nhap gia phong: ");
            roomsCost[i] = scanner.next();
            System.out.println("Nhap trang thai phong: ");
            status[i] = scanner.next();
            i++; // Tăng biến đếm phần tử lên
        }
        try(FileWriter fw = new FileWriter(file, true)){
            // FileWriter Dùng cái này để ghi vào file
            for(int j=i-n;j<i;j++){
                fw.write("\n"+roomNumber[j] + "," + type[j] + "," + roomsCost[j] + "$," + status[j]); // Ghi từng dòng vào file
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ghi file that bai");
        }
    }
}
public class BTL_LE_Vuong {
    @SuppressWarnings({ "static-access", "resource" })
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        rooms Room = new rooms();
        while(true){
            System.out.println("Vui long chon chuong trinh!");
            System.out.println("1. Đoc từ tệp ra!");
            System.out.println("2. Thêm phòng vào tệp!");
            System.out.println("3. Chỉnh sửa phòng đã có sẵn");
            int n = scanner.nextInt();
            switch(n){
                case 1:
                    Room.CheckFileRoom();
                    break;
                case 2:
                    Room.GhiFile();
                    break;
                case 3:
                
                default:
                    System.out.println("Nhap sai, vui long nhap lai");
            }
        }
    }
}
