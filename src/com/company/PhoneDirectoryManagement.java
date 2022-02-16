package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneDirectoryManagement implements ReadFile, WriteFile {
    private List<PhoneDirectory> phoneDirectories = new ArrayList<>();

    public PhoneDirectoryManagement() {
    }

    public int totalContact() {
        return phoneDirectories.size();
    }

    public void displayAllContact() {
        for (PhoneDirectory phoneDirectory :
                phoneDirectories) {
            System.out.println(phoneDirectory);
        }
    }

    public void addNewContact(PhoneDirectory phoneDirectory) {
        this.phoneDirectories.add(phoneDirectory);

    }

    public PhoneDirectory getContact(int index) {
        return phoneDirectories.get(index);
    }

    public int findContactByName(String fullName) {
        int index = -1;
        for (int i = 0; i < phoneDirectories.size(); i++) {
            if (phoneDirectories.get(i).getFullName().equals(fullName)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int findContactByID(String id) {
        int index = -1;
        for (int i = 0; i < phoneDirectories.size(); i++) {
            if (phoneDirectories.get(i).getFullName().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean updateContactByID(String id, PhoneDirectory phoneDirectory) {
        int index = findContactByName(id);
        if (index != -1) {
            phoneDirectories.set(index, phoneDirectory);
            try {
                writeFile("PhoneDirectoryList.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public boolean removeContactByID(String id) {
        int index = findContactByName(id);
        if (index != -1) {
            phoneDirectories.remove(index);
            try {
                writeFile("PhoneDirectoryList");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        } else return false;
    }

    public void writeBillFileText(ArrayList<PhoneDirectory> phoneDirectories,  String path) throws IOException {
        FileWriter billFileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(billFileWriter);
        for (PhoneDirectory phoneDirectory : phoneDirectories) {
            bufferedWriter.write(phoneDirectory.getId() + "\t\t" + phoneDirectory.getFullName()
                    + "\t\t\t" + phoneDirectory.getHomeTown() + "\t\t\t" + phoneDirectory.getPhoneNumber() +
                    phoneDirectory.getEmail() + phoneDirectory.getCategory() + "\n");
        }
        bufferedWriter.close();
        billFileWriter.close();
    }

    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(is);
        phoneDirectories = (List<PhoneDirectory>) objectInputStream.readObject();
        is.close();
        objectInputStream.close();
    }

    public void writeFile(String path) throws IOException {
        OutputStream outputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(phoneDirectories);
        outputStream.close();
        objectOutputStream.close();
    }
}

