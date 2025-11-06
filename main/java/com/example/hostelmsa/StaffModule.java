package com.example.hostelmsa;

public class StaffModule {
    String staffId, staffPassword, staffFullName, staffHostelAllocation, staffContact, staffEmail, staffAddress;
    public StaffModule() {
    }

    public StaffModule(String staffId, String staffPassword, String staffFullName, String staffHostelAllocation, String staffContact, String staffEmail, String staffAddress) {
        this.staffId = staffId;
        this.staffPassword = staffPassword;
        this.staffFullName = staffFullName;
        this.staffHostelAllocation = staffHostelAllocation;
        this.staffContact = staffContact;
        this.staffEmail = staffEmail;
        this.staffAddress = staffAddress;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStaffPassword() {
        return staffPassword;
    }

    public void setStaffPassword(String staffPassword) {
        this.staffPassword = staffPassword;
    }

    public String getStaffFullName() {
        return staffFullName;
    }

    public void setStaffFullName(String staffFullName) {
        this.staffFullName = staffFullName;
    }

    public String getStaffHostelAllocation() {
        return staffHostelAllocation;
    }

    public void setStaffHostelAllocation(String staffHostelAllocation) {
        this.staffHostelAllocation = staffHostelAllocation;
    }

    public String getStaffContact() {
        return staffContact;
    }

    public void setStaffContact(String staffContact) {
        this.staffContact = staffContact;
    }

    public String getStaffEmail() {
        return staffEmail;
    }

    public void setStaffEmail(String staffEmail) {
        this.staffEmail = staffEmail;
    }

    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }
}
