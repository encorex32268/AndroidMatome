package com.lihan.androidmatome.activity.retrofitapi.model

data class User(
    val _id: String,
    val age: Int,
    val balance: String,
    val company: String,
    val email: String,
    val eyeColor: String,
    val favoriteFruit: String,
    val gender: String,
    val greeting: String,
    val guid: String,
    val index: Int,
    val isActive: Boolean,
    val latitude: Double,
    val longitude: Double,
    val name: String,
    val phone: String,
    val registered: String
){
    override fun toString(): String {
        return "User (_id= \n '$_id', age=\n $age, balance= \n '$balance', company=\n '$company', email=\n '$email', eyeColor=\n '$eyeColor', favoriteFruit=\n '$favoriteFruit', gender=\n '$gender', \n greeting=\n '$greeting', guid=\n'$guid', index=\n $index, isActive=\n $isActive, latitude=\n $latitude, longitude=\n $longitude, name=\n '$name', phone=\n '$phone', registered=\n '$registered')"
    }
}