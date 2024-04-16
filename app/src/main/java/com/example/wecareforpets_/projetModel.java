package com.example.wecareforpets_;

public class projetModel {
   private String petCategory, breed, sex, price, purpose, ownerName, ownerEmail, ownerPhone, ownerAddress;
   private String imageViewUploadedPet;

    public projetModel(String petCategory, String breed, String sex, String price, String purpose, String ownerName, String ownerEmail, String ownerPhone, String ownerAddress, String imageViewUploadedPet) {
        this.petCategory = petCategory;
        this.breed = breed;
        this.sex = sex;
        this.price = price;
        this.purpose = purpose;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
        this.ownerPhone = ownerPhone;
        this.ownerAddress = ownerAddress;
        this.imageViewUploadedPet = imageViewUploadedPet;
    }

    public  projetModel(){


    }

    public String getPetCategory() {
        return petCategory;
    }

    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getImageViewUploadedPet() {
        return imageViewUploadedPet;
    }

    public void setImageViewUploadedPet(String imageViewUploadedPet) {
        this.imageViewUploadedPet = imageViewUploadedPet;



    }
}
