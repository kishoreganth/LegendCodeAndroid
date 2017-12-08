# GPS Tacker 

This demo is for tracking the current location using GPSTracker for android. 

### Permission 

Add these permissions to the android project manifest file to get access the location.
```
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
     <uses-permission android:name="android.permission.INTERNET" />
```

### Code Guides 

Add the GPS Tracker class and impllement locationlistener 
```
public class GPSTracker extends Service implements LocationListener {
.....
..
....
}
```
Use the GPS Tracker java class in this demo code and run it in your app like this demo . Cheers :+1:
