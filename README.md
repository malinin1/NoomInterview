I attempted to balance not over-engineering / over-complicating this
project and keeping the code somewhat true to how it would be written in 
production.

With that, there are a few things I wanted to call out: 

Using @ActivityScope for the food search Dagger component is contrived.
In a larger application, we could go over the possibilities for scoping
feature components. If Dagger component initialization were to be done off 
the main thread, we would also need to handle concurrency. The Dagger code
in its current form would need changes to scale to a large application.

In production, we would probably want to go with Dagger.Android or Hilt
to reduce the Dagger boilerplate and standardize the injection of 
Android components. 

In production, we could consider backgrounding the initialization of 
expensive components; this would come with the need to consider 
concurrency issues as well. Moving the Dagger component initialization
to the background now seemed like over-engineering.

Loading/Error states are currently not implemented in the UI; however the 
code is designed to allow them to be added easily.

FoodSearchRepository is currently not doing anything to handle concurrency.
In production, we would consider if we wanted to e.g. deduplicate requests
if we had multiple concurrent requests with the same parameters. This 
seemed like over-engineering to build at the moment. 

The code is designed to be testable; in production we would expect a 
feature to be tested before merging it in. Injecting schedulers is 
intended to make testing easier by allowing us to inject test schedulers.

In production, we would expect the raw API return type (FoodSearchResponse)
to be transformed into a separate model; this felt like over-engineering
to do at the moment. 



