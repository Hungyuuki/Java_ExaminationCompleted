package com.company;

import java.io.IOException;
import java.util.Scanner;

public class MainPhoneDirectory {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        PhoneDirectoryManagement phoneDirectoryManagement = new PhoneDirectoryManagement();
        int choiceMainmenu = 1;
        do {
            mainMenu();
            choiceMainmenu = scanner.nextInt();
            scanner.nextLine();
            switch (choiceMainmenu) {
                case 1:
                    displayAllContactStepByStep(phoneDirectoryManagement);
                    break;
                case 2:
                    addNewContact(phoneDirectoryManagement);
                    break;
                case 3:
                    UpdateContactStepByStep(phoneDirectoryManagement);
                    break;
                case 4:
                    removeContactByIDStepByStep(phoneDirectoryManagement);
                    break;
                case 5:
                    findContactByIDStepByStep(phoneDirectoryManagement);
                    break;
                case 6:
                    writeFileStepByStep(phoneDirectoryManagement);
                    break;
                case 7:
                    readFileStepByStep(phoneDirectoryManagement);
                    break;
            }
        } while (choiceMainmenu != 0);
    }

    private static void readFileStepByStep(PhoneDirectoryManagement phoneDirectoryManagement) {
        try {
            phoneDirectoryManagement.writeFile("ContactList.csv");
        } catch (IOException e) {
        }
    }

    private static void writeFileStepByStep(PhoneDirectoryManagement phoneDirectoryManagement) {
        System.out.println("Đọc file");
        try {
            phoneDirectoryManagement.readFile("ContactList.csv");
            System.out.println("Đọc file thành công");
            displayAllContactStepByStep(phoneDirectoryManagement);
        } catch (IOException | ClassNotFoundException e) {
        }
    }

    private static void findContactByIDStepByStep(PhoneDirectoryManagement phoneDirectoryManagement) {
        System.out.println("Tìm sách theo tên liên lạc");
        System.out.println("Mời nhập chính xác thông tin: ");

        String fullName = scanner.nextLine();
        int index = phoneDirectoryManagement.findContactByName(fullName);
        if (index != -1) {
            System.out.println("Thông tin liên lạc cần tìm: " + phoneDirectoryManagement.getContact(index));
        } else System.err.println("Không tìm thấy contact");
    }

    private static void removeContactByIDStepByStep(PhoneDirectoryManagement phoneDirectoryManagement) {
        System.out.println("Xóa thông tin liên lạc:");
        System.out.println("Nhập ID liên lạc cần xóa:");
        String id = scanner.nextLine();
        boolean isDeleted = phoneDirectoryManagement.removeContactByID(id);
        if (isDeleted)
            System.out.println("Xóa thành công");
        else System.err.println("Không xóa được vì không có ID này");
    }

    private static void UpdateContactStepByStep(PhoneDirectoryManagement phoneDirectoryManagement) {
        System.out.println("Chỉnh sửa thông tin liên lạc: ");
        System.out.println("Nhập tên cần chỉnh sửa: ");
        String id = scanner.nextLine();
        int index = phoneDirectoryManagement.findContactByID(id);
        if (index != -1) {
            PhoneDirectory phoneDirectory = inputNewContactStepByStep();
            phoneDirectoryManagement.updateContactByID(id, phoneDirectory);
            System.out.println("Chỉnh sửa OK.");
        } else
            System.err.println("Không update được vì không có tên này.");
    }

    private static void addNewContact(PhoneDirectoryManagement phoneDirectoryManagement) {
        System.out.println("Thêm liên lạc mới: ");
        PhoneDirectory phoneDirectory = inputNewContactStepByStep();
        phoneDirectoryManagement.addNewContact(phoneDirectory);
    }


    private static PhoneDirectory inputNewContactStepByStep() {
        System.out.println("Nhập ID: ");
        String id = scanner.nextLine();
        System.out.println("Nhập họ tên: ");
        String fullName = scanner.nextLine();
        System.out.println("Nhập giới tính: ");
        String gender = scanner.nextLine();
        System.out.println("Nhập số điện thoại");
        String phoneNumber = scanner.nextLine();
        System.out.println("Nhập nhóm danh bạ");
        String category = scanner.nextLine();
        System.out.println("Nhập địa chỉ: ");
        String homeTown = scanner.nextLine();
        System.out.println("Nhập Ngày sinh");
        String dateOfBirth = scanner.nextLine();
        System.out.println("Nhập Email@");
        String email = scanner.nextLine();
        PhoneDirectory phoneDirectory = new PhoneDirectory(id, fullName, gender, phoneNumber, homeTown, dateOfBirth, email);
        return phoneDirectory;
    }


    private static void displayAllContactStepByStep(PhoneDirectoryManagement phoneDirectoryManagement) {
        int total = phoneDirectoryManagement.totalContact();
        if (total == 0)
            System.out.println("Danh sách rỗng!!!");
        else
            phoneDirectoryManagement.displayAllContact();
    }

    private static void mainMenu() {
        System.out.println("Danh bạ điện thoại");
        System.out.println("1. Hiển thị danh bạ: ");
        System.out.println("2. Thêm liên lạc mới: ");
        System.out.println("3. Cập nhật thông tin liên lạc theo ID: ");
        System.out.println("4. Xóa liên lạc theo ID: ");
        System.out.println("5. Tìm kiếm: ");
        System.out.println("6. Đọc vào file: ");
        System.out.println("7. Ghi vào file: ");
        System.out.println("0. Thoát!!!");
        System.out.println("Mời chọn chức năng");
    }
}
