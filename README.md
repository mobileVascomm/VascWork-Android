# VascWork-Android
Clean code architecture and utils for easy development android application
  1. Module Arcitecture
  2. Recyclerview Adapter
  3. Extension
  4. Validation Form
  
## Usage 
Add a dependency to your `build.gradle`:
```
allprojects {
    repositories {
	...
	maven { url 'https://jitpack.io' }
    }
}
```
```
dependencies {
	  implementation 'com.github.mobileVascomm:VascWork-Android:v1.0'
}
```

## Module Architecture
1. Implement ViewState on fragment or Activity
```kotlin
class ActivitySampleModule:AppCompatActivity(),ViewStateInterface {
    override fun onSuccess(result: Result) {    }

    override fun onFailure(result: Result) {    }

    override fun onLoading(result: Result) {    }

}
```
2. Create Module file with implement Module
```kotlin
interface Sample{
    fun sample1()
    fun sample2()
    fun sample3()
}

class ModuleSample constructor(iView:ViewStateInterface,context: Context):Module(iView,context),Sample{
    companion object{
        const val TAG_SAMPLE1 = "sample_1"
        const val TAG_SAMPLE2 = "sample_2"
        const val TAG_SAMPLE3 = "sample_3"
    }

    override fun sample1() {
        event(TAG_SAMPLE1){
            it.success("00","Success",dummy)
        }
    }

    override fun sample2() {
        event(TAG_SAMPLE2){
            it.failure("99","Failure","")
        }
    }

    override fun sample3() {
        event(TAG_SAMPLE3){
            it.success(data = dummy2)
        }
    }

}

```

3. Use Event with tag to create network request or send data to ViewStateInterface (important)
``` kotlin
    event(TAG_SAMPLE1){
            it.success("00","sample data berhasil",dummy)
    }

```

4. Function user to communication from Module to ViewStateInterface (Code, Message and Data)
``` kotlin
    // success state
       it.success("00","Successl",dummy)

    //failure state
       it.failure("99","Failure","")

    // loading state
       it.loading("88","Loading",true)

```

## Recyclerview Adapter
1. Create data class
```kotlin
data class User(val id:String,val nama:String)
```
2. Create ViewHolder class
```kotlin
class UserViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    fun onBind(user:User){
        itemView.user_name.text = user.nama
    }
}
```
3. Add Adapter into Recyclerview
```kotlin
list_login.adapter  = object :RecyclerviewAdapter<User,UserViewHolder>(R.layout.list_user,UserViewHolder::class.java,User::class.java,listUser){
            override fun bindView(holder: UserViewHolder, model: User, position: Int) {
                holder.onBind(model)
            }
        }
```

## Extension
  1. Extension EditText
```kotlin
/*  Auto format currency Rupiah. Example : 1.000.000 */
EditText.setCurrencyFormat()

 /* Auto format card number. Example : 0000 1234 1241 1234 */
EditText.setCardNumberFormant()

```
  2. String
  ```kotlin
/* Convert nominal to currency format. Contoh : 100000 -> Rp100.000 */
fun String.convertRupiaCurrency()

/* Convert currency format ke nominal. Contoh : Rp100.000 -> 100000 */
fun String.convertToNoninal()

/* Convert string ke Base64 */
fun String.toBase64()

  ```
  
##  Validation Form
  1. Implement ValidationInterface
```kotlin
class SampleValidation :AppCompatActivity(),ValidationInterface  {
    override fun validationSuccess(data: HashMap<String, String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
```
  2. Register field for validation form
  ```kotlin
   validation.registerField(field1,input1,"username",
            mutableListOf(RequireRule("Field tidak boleh kosong")))
        validation.registerField(field2,input2,"email", 
            mutableListOf(EmailRule("Email tidak valid")))
        validation.registerField(field3,input3,"password", 
            mutableListOf( RegexRule("Format password tidak valid ","(?=.*[0-9])(?=.*[a-z]).{8,}"),
            LenghtRule("Minimal length 10 digit dan maksimal 12",10,12)))
        validation.registerField(field4,input4,"confirm",
            mutableListOf(ConfrimationRule("Confrimasi password tidk sama",field3)))
  
  ```
   3. Proses Validation 
   ````kotlin
      validation.validation()
   ````
