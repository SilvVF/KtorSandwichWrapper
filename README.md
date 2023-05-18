# KtorSandwichWrapper

#wrapper for Sandwich https://github.com/skydoves/sandwich 
#where the requests are made from the Ktor HttpClient rather than a retrofit instance

### to use copy the KtorSandwhich Module into the root project directory 
### add implementation(project(":KtorSandwhich")) to the app build.gradle file. 
### This module relies on inline functions and will only work if the target JVM matches that of the app module


## Basic Usage
```
// create a client this will give access to all of the inline Extension function for the KSandwhich interface
val client: KSandwichClient = KSandwichClient.create()
```

## the KSandwhich has four extension functions available for making network calls.
.post()
.put()
.get()
.patch()

## The client engine can be changed and will use Ktor CIO by default with no additional config
this can also be changed through the KSandwhichInitializer object

#Global response operators
to add a global resonse operator call KSandwichClient.addGlobalOperator(operator: SandwichOperator)
the SandwichOperator will accept the same ApiResonseOprator and ApiResonseOpratorSuspendOperator as the Sandwich library

these operate equivalent to the ktor HttpClient Methods 
All of the methods will return and ApiResponse that is almost equivalent to the one from Sandwhich.
The ApiResponse has many extension funcitons available to it that can help when handling api responses. 

to see all of the extension go to KtorSandwich/io/silv/ktorsandwich/ApiResponseExtensions

