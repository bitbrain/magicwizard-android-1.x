MagicWizard for Android
---

Android app for "Magic - The Gathering" players in order to count life points.

## Download

You can download the newest version [here](http://magicwizard.my-reality.de).

## Development

This app is still under development and we want to improve this software each day. We play 'Magic - The Gathering' and we want to put our best effort into this project. If you attempt to join us, read the next section.

### Requirements

To work on this project, you will need:

* Fork this project via ```git clone repository-url```
* Basic experience in [Java and Android development](http://www.youtube.com/playlist?list=PL301ACBB31D739F72)
* [Eclipse IDE](http://www.eclipse.org/downloads/) (Indigo preferred)
* [Android SDK](http://developer.android.com/sdk/index.html) including ADT
* [SlidingMenu](https://github.com/jfeinstein10/SlidingMenu) library on GitHub

To configure the sliding menu, download the code (or fork it via git). Afterwards you open Eclipse and follow the steps:

1. Go to ```File -> New -> Others..```
2. Afterwards select ```Android -> Android Project from Existing Code```
3. Select the ```library``` folder of the downloaded sliding menu project
4. Click ```finish```

Everything complete? Then you should know, that you can fork this repository on order to make improvements! Just go on and make a pull request. Maybe it has a chance to get approved!

#### Linux hint

If you are using a 64 bit based architecture, install the ia libraries first, in order to generate the ```R.java``` files properly:

```text
sudo apt-get install ia32-libs
```

It is also important to place the ```SlidingMenu``` project folder in the same folder with the ```magicwizard-android``` project folder.

### Project Structure

The project structure looks as follows:

```
src/myreality/development/magicwizard/    <- Contains sources    
    activities/                           <- Contains all activities
    components/                           <- Contains all business logic components
    layouts/                              <- Provides custom layouts
    widgets/                              <- Provides custom widgets
    util/                                 <- Contains utility classes and interfaces
    
res/                                      <- Contains resources (images,meta data)

AndroidManifest.xml                       <- Contains general Android configuration

README.md                                 <- Documentation of the software
```

### Getting started

The basic concept of this app is to create components and map them to a specific view. Each view has an own id. Without going into much detail, you can write an own component:

```java
public class MyComponent implements Component {
	
	@Override
	public void handle(Activity context) {
		// Do something here with the context!
	}
}
```

Afterwards you have to register your component in the ```SimpleComponentHandlerFactory``` class and map it to a specific input:

```java
handler.addComponent(R.id.btn_my_action, new MyComponent());
```
The handler will be processed by the current activity context. 
