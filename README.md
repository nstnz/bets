# bets
A mobile app for betting on football matches. There are 2 views in the app: one for making predictions on upcoming matches and the other for seeing the actual results compared to the predictions made in the previous view.

# features
- all matches are loaded from mock server (mocky.io) and stored in Room database
- the app always opens the last screen user was
- app has multi-module structure: navigation module for navigation, core module for base classes and methods, bets module as feature module and app module as main application.
- navigation architecture has opportunity to easily add new modules
- app won't make HTTP requests if it less than minute from the last request, it will load data from database instead
- use Reset button to start over


| Bets view  | Adding bets | Results view |
| ------------- | ------------- | ------------- |
| ![photo_2022-03-05_09-39-14](https://user-images.githubusercontent.com/37050614/156871806-190b3b9d-3d55-4d68-8e4e-0117d317f630.jpg) | ![photo_2022-03-05_09-39-18](https://user-images.githubusercontent.com/37050614/156871805-d409058f-aefa-4743-bdba-ca1677970991.jpg) | ![photo_2022-03-05_09-39-23](https://user-images.githubusercontent.com/37050614/156871802-a385fd00-02d4-415a-ba00-5eed4b41377a.jpg) |

> BTW, I don't know why there are some circles on my screenshots :) The app doesn't have them.

# libraries
- Room
- Dagger Hilt
- Retrofit / Gson
- Kotlin coroutines for multithreading
- Android architecture components
