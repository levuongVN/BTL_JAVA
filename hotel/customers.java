package hotel;
import java.io.BufferedReader; // Các thư viện cần có để làm việc với file
import java.io.FileReader; // Thư viện này để đọc
import java.io.FileWriter;
import java.io.IOException; // In ra các thông báo lỗi
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/*
- Class Khách hàng
+ họ tên (Kế thừa từ class nhân viên)
+ mã khách hàng (Kế thừa từ class nhân viên)
+ Ngày tháng năm sinh
+ Số cccd
+ Số đt
+ Ngày đặt phòng
+ Ngày đến
+ Ngày đi
+ Đánh giá chất lượng phục vụ
 */
class custumVariables{
    protected ArrayList<String> NameCustomers = new ArrayList<>();
    protected ArrayList<String> NumberRoomCustomers = new ArrayList<>();
    protected ArrayList<String> IdCustomers = new ArrayList<>();
    protected ArrayList<String> DateOfBirthCustomers = new ArrayList<>();
    protected ArrayList<String> CccdCustomers = new ArrayList<>();
    protected ArrayList<String> PhoneCustomers = new ArrayList<>();
    protected ArrayList<String> DateBookingCustomers = new ArrayList<>();
    protected ArrayList<String> DateArrivalCustomers = new ArrayList<>();
    protected ArrayList<String> DateDepartureCustomers = new ArrayList<>();
    protected ArrayList<String> CheckSttCustomers = new ArrayList<>();
}
class customersCheckFile extends custumVariables{
    String file = "/Users/levuong2005/Documents/GitHub/BTL_JAVA/Customers.csv";
    String line = "";
    String limiter = ",";
    public boolean Check_FileCus(){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            while((line = reader.readLine()) != null){
                String[] data = line.split(limiter);
                IdCustomers.add(data[0]);
                NumberRoomCustomers.add(data[1]);
                NameCustomers.add(data[2]);
                DateOfBirthCustomers.add(data[3]);
                CccdCustomers.add(data[4]);
                PhoneCustomers.add(data[5]);
                DateBookingCustomers.add(data[6]);
                DateArrivalCustomers.add(data[7]);
                DateDepartureCustomers.add(data[8]);
                CheckSttCustomers.add(data[9]);
            }
            System.out.println("Đọc file thành công");
            // for(int i = 0;i<IdCustomers.size();i++){
            //     System.out.println(IdCustomers.get(i)+","+NumberRoomCustomers.get(i)+","+NameCustomers.get(i)+","+DateOfBirthCustomers.get(i)+","+CccdCustomers.get(i)+","+PhoneCustomers.get(i)+","+DateBookingCustomers.get(i)+","+DateArrivalCustomers.get(i)+","+DateDepartureCustomers.get(i)+","+CheckSttCustomers.get(i));
            // }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
class CheckRoomsNumber extends CheckFile {
    public ArrayList<String> NumberRooms = new ArrayList<>();
    public void checkAndAddRooms() {
        if (Check_File()) {
            for (int i = 0; i < NumberRoom.size(); i++) {
                NumberRooms.add(NumberRoom.get(i));
            }
        }else{
            System.out.println("Không tìm thấy file");
        }
    }
}
class AddCustomers extends customersCheckFile{
    Scanner scanner = new Scanner(System.in);
    public boolean add(){
        if(Check_FileCus()){
            System.out.print("Nhập số lượng khách hàng muốn thêm: ");
            int num = scanner.nextInt();
            scanner.nextLine();
            for(int i = 0;i<num;i++){
                System.out.print("Nhập mã khách hàng: ");
                String id = scanner.nextLine();
                for(int j = 0;j<IdCustomers.size();j++){
                    if(IdCustomers.get(j).equals(id)){
                        System.out.println("Mã khách hàng đã tồn tại, vui lòng nhập lại");
                        return false;
                    }
                }
                IdCustomers.add(id);
                System.out.println("Nhập số phòng: ");
                int numberRoom = scanner.nextInt();
                int check = 0;
                CheckRoomsNumber checkRoomsNumber = new CheckRoomsNumber();
                for(int j =0;j<checkRoomsNumber.NumberRooms.size();j++){
                    if(checkRoomsNumber.NumberRooms.get(j).equals(String.valueOf(numberRoom))){
                        for(int k = 0;k<NumberRoomCustomers.size();k++){
                            if(NumberRoomCustomers.get(k).equals(String.valueOf(numberRoom))){
                                System.out.println("Số phòng đã tồn tại, vui lòng nhập lại");
                                return false;
                            }
                        }
                        break;
                    }else{
                        check++;
                    }
                }
                if(check == checkRoomsNumber.NumberRooms.size()){
                    System.out.println("Số phòng không tồn tại, vui lòng nhập lại");
                    return false;
                }else{
                    NumberRoomCustomers.add(String.valueOf(numberRoom));
                }
                scanner.nextLine();
                System.out.println("Nhập họ tên khách hàng: ");
                NameCustomers.add(scanner.nextLine());
                System.out.println("Nhập ngày tháng năm sinh (dd/mm/yyyy): ");
                String dateInput = scanner.nextLine();
                try{
                    int age = LocalDate.now().getYear() - LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear();
                    if(age < 18){
                        System.out.println("Khách hàng phải trên 18 tuổi");
                        return false;
                    }else{
                        DateOfBirthCustomers.add(dateInput);
                    }
                }catch(DateTimeParseException e){
                    System.out.println("Ngày tháng năm sinh không hợp lệ, vui lòng nhập lại");
                    return false;
                }
                System.out.println("Nhập số cccd: ");
                CccdCustomers.add(scanner.nextLine());
                System.out.println("Nhập số điện thoại: ");
                String phoneInput = scanner.nextLine().trim();
                // Remove country code if present
                if (phoneInput.startsWith("+84")) {
                    phoneInput = "0" + phoneInput.substring(3);
                } else if (phoneInput.startsWith("84")) {
                    phoneInput = "0" + phoneInput.substring(2);
                }
                
                // Check if the phone number is valid
                if (phoneInput.matches("0[0-9]{9}")) {
                    // Format the phone number
                    String formattedPhone = phoneInput.replaceFirst("(\\d{4})(\\d{3})(\\d{3})", "$1 $2 $3");
                    PhoneCustomers.add(formattedPhone);
                } else {
                    System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập 10 chữ số, bắt đầu bằng số 0.");
                    return false;
                }
                System.out.println("Nhập ngày đặt phòng (dd/mm/yyyy): ");
                String dateBooking = scanner.nextLine();
                try {
                    // LocalDate.parse(dateBooking, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    // Kiểm tra xem ngày đặt phòng có trước ngày hiện tại không
                    if(LocalDate.parse(dateBooking, DateTimeFormatter.ofPattern("dd/MM/yyyy")).isBefore(LocalDate.now())){
                        DateBookingCustomers.add(dateBooking);
                    }else{
                        System.out.println("Ngày đặt phòng không hợp lệ, vui lòng nhập lại");
                        return false;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Ngày đặt phòng không hợp lệ, vui lòng nhập lại");
                    return false;
                }
                System.out.println("Nhập ngày đến (dd/mm/yyyy): ");
                String dateArrival = scanner.nextLine();
                System.out.println("Nhập ngày đi (dd/mm/yyyy): ");
                String dateDeparture = scanner.nextLine();
                if(LocalDate.parse(dateArrival, DateTimeFormatter.ofPattern("dd/MM/yyyy")).isBefore(
                    LocalDate.parse(dateBooking, DateTimeFormatter.ofPattern("dd/MM/yyyy")))){
                        // Kiểm tra xem ngày đến có trước ngày đặt phòng không
                    System.out.println("Ngày đến không hợp lệ, vui lòng nhập lại");
                    return false;
                }else if(LocalDate.parse(dateDeparture, DateTimeFormatter.ofPattern("dd/MM/yyyy")).isBefore(
                    LocalDate.parse(dateArrival, DateTimeFormatter.ofPattern("dd/MM/yyyy")))){
                        // Kiểm tra xem ngày đi có trước ngày đến không
                        System.out.println("Ngày đi không hợp lệ, vui lòng nhập lại");
                        return false;
                }else{
                    // Nếu ngày đến và ngày đi hợp lệ, thêm vào danh sách
                    DateArrivalCustomers.add(dateArrival);
                    DateDepartureCustomers.add(dateDeparture);
                    if(LocalDate.parse(dateArrival,DateTimeFormatter.ofPattern("dd/MM/yyyy")).isBefore(
                        LocalDate.now()) && LocalDate.parse(dateDeparture,DateTimeFormatter.ofPattern("dd/MM/yyyy")).isAfter(
                            LocalDate.now())){
                        CheckSttCustomers.add("Check-in");
                    }else if(LocalDate.parse(dateDeparture,DateTimeFormatter.ofPattern("dd/MM/yyyy")).isBefore(
                        LocalDate.now())){
                            CheckSttCustomers.add("Check-out");
                    }else{
                        CheckSttCustomers.add("Đang ở");
                    }
                }
                System.out.println("Thêm khách hàng thành công");
            }
            try (Writer writer = new FileWriter(file,true)) {
                for(int i = 0;i<IdCustomers.size();i++){
                    writer.write(IdCustomers.get(i)+","+NumberRoomCustomers.get(i)+","+NameCustomers.get(i)+","+DateOfBirthCustomers.get(i)+","+CccdCustomers.get(i)+","+PhoneCustomers.get(i)+","+DateBookingCustomers.get(i)+","+DateArrivalCustomers.get(i)+","+DateDepartureCustomers.get(i)+","+CheckSttCustomers.get(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Không tìm thấy file");
            return false;
        }
        return true;
    }
}
class DisplayCustomers extends KhachSan{
    Scanner scanner = new Scanner(System.in);
    customersCheckFile CheckFile = new customersCheckFile();
    @Override
    public void display(){
        if(CheckFile.Check_FileCus()){
            if(CheckFile.IdCustomers.size() == 1){
                System.out.println("Không có khách hàng nào, bạn muốn thêm khách hàng không? (Y/N)");
                String choice = scanner.nextLine();
                if(choice.equals("Y") || choice.equals("y")){
                    AddCustomers addCustomers = new AddCustomers();
                    addCustomers.add();
                }else{
                    System.out.println("Không có thông tin của khách hàng nào hiển thị cả!");
                }
            }else{
                for(int i = 1;i<CheckFile.IdCustomers.size();i++){
                    System.out.println(CheckFile.IdCustomers.get(i)+","+CheckFile.NumberRoomCustomers.get(i)+","+CheckFile.NameCustomers.get(i)+","+CheckFile.DateOfBirthCustomers.get(i)+","+CheckFile.CccdCustomers.get(i)+","+CheckFile.PhoneCustomers.get(i)+","+CheckFile.DateBookingCustomers.get(i)+","+CheckFile.DateArrivalCustomers.get(i)+","+CheckFile.DateDepartureCustomers.get(i)+","+CheckFile.CheckSttCustomers.get(i));
                }
            }
        }else{
            System.out.println("Không tìm thấy file");
        }
    }
}
class CheckInCheckOut extends customersCheckFile{
    public void checkInCheckOut(){
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        
        if(Check_FileCus()){
            System.out.println("Nhập số phòng hoặc mã khách hàng bạn muốn kiểm tra!");
            String choice = scanner.nextLine();
            boolean found = false;
            for(int i = 0; i < NumberRoomCustomers.size(); i++){
                if(NumberRoomCustomers.get(i).equals(choice) || IdCustomers.get(i).equals(choice)){
                    found = true;
                    if(CheckSttCustomers.get(i).equals("Đang ở")){
                        System.out.println("Khách hàng đang ở phòng");
                    }else if(CheckSttCustomers.get(i).equals("Check-in")){
                        System.out.println("Khách hàng đã check-in");
                    }else if(CheckSttCustomers.get(i).equals("Check-out")){
                        System.out.println("Khách hàng đã check-out");
                    }
                    break;
                }
            }
            if(!found){
                System.out.println("Không tìm thấy!");
            }
        }else{
            System.out.println("Không tìm thấy file");
        }
    }
}
public class customers {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("----------------------------------");
            System.out.println("Chương trình quản lý khách hàng");
            System.out.println("Vui lòng chọn chương trình");
            System.out.println("0. Thoát");
            System.out.println("1. Thêm thông tin khách hàng mới");
            System.out.println("2. Hiển thị thông tin khách hàng");
            System.out.println("3. Xác nhận Check-in/Check-out");
            System.out.println("4. Tìm kiếm thông tin khách hàng");
            System.out.println("5. Sửa thông tin khách hàng");
            System.out.println("6. Xóa thông tin khách hàng");
            System.out.println("----------------------------------");
            choice = scanner.nextInt();
            switch (choice) {
            case 0:
                System.out.println("Thoát chương trình");
                break;
            case 1:
                AddCustomers addCustomers = new AddCustomers();
                addCustomers.add();
                break;
            case 2:
                DisplayCustomers displayCustomers = new DisplayCustomers();
                displayCustomers.display();
                break;
            case 3:
                CheckInCheckOut checkInCheckOut = new CheckInCheckOut();
                checkInCheckOut.checkInCheckOut();
                break;
                
            default:
                System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
                break;
        }
        } while (choice!=0);
    }
    
}
