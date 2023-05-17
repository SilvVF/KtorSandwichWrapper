# KtorSandwichWrapper

#wrapper for Sandwhich https://github.com/skydoves/sandwich 
#where the requests are made from the Ktor HttpClient rather than a retrofit instance

### to use copy the KtorSandwhich Module into the root project directory and add implementation(project(":KtorSandwhich")) to the app build.gradle file. This module relies on inline functions and will only work if the target JVM matches that of the app module


## Basic Usage
```
// create a client this will give access to all of the inline Extension function for the KSandwhich interface
val client: KSandwichClient = KSandwichClient.create()
```

# the KSandwhich has four extension functions avalable
.post()
.put()
.get()
.patch()

these operate equivalent to the ktor HttpClient Methods 
All of the methods will return and ApiResponse that is almost equivalent to the one from Sandwhich.
The ApiResponse has many extension funcitons available to it that can help when handle api responses. 

to see all of the extension go to KtorSandwich/io/silv/ktorsandwich/ApiResponseExtensions
