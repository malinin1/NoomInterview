I attempted to balance not over-engineering / over-complicating this
project and keeping the code somewhat true to how it would be written in 
production.

With that, there are a few things I wanted to call out: 

In a real application, we would need to manage the Dagger scope 
for FoodSearchComponent (i.e., initialize the component when entering 
the scope, and release the component when exiting the scope).
Currently, we do not release FoodSearchComponent, and ActivityScope
functionally the same as the parent @Singleton scope.
In production, we would not have a child scope share the same 
lifetime as @Singleton. 
The separate FoodSearchComponent Dagger component and separate
@ActivityScope are intended to show the thought process of how to split up 
components as the app scales; the implementation is knowingly simplified
and the implementation would need to be completed for a real application. 

In production, we would probably want to go with Dagger.Android or Hilt
to reduce the Dagger boilerplate and standardize the injection of 
Dagger components. 

In production, we could consider backgrounding the initialization of 
expensive components; this would come with the need to consider 
concurrency issues as well. Moving the current Dagger component initialization
to the background and dealing with concurrency now seemed like over-engineering.

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



