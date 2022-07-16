# Competencias digitales
Es una aplicación que se usó para trabajo comunitario sobre la enseñanza de competencias digitales en los temas de **compartir contenido por medios digitales sencillos**.

La aplicación consta de 3 partes donde:

1. Syllabus sobre los temas de aprendizaje.
2. Un exámen sencillo que pone a prueba los conocimientos una vez finalizado el syllabus.
3. La lista de calificaciones obtenidas en el exámen.

Ahora se actualizó con [Kotlin][1], [Jetpack Compose][2] para el diseño de la UI y [Lottie Files][3] para los archivos de animación, ademas se integra con [Firebase Authentication][4] para ingresar al usuario en Firebase si es necesario y [Firestore][5] para guardar las calificaciones obtenidas en el exámen, el proyecto aplica el patrón de arquitectura MVVM.

# Capturas de pantalla

![alt text](https://i.ibb.co/r7hQ5jV/screen-shot-app.png)

A continuacion se presentan las diferentes tecnologías y herramientas utilizadas en esta aplicación:

## Componentes de Android:
* [Jetpack Compose][2]
* [ViewModel][6]
* [Compose Navigation][7]
* [Shared Preferences][14]

## Productos de Firebase:
* [Firebase Authentication][4]
* [Firestore][5]

## Injección de dependencias:
* [Hilt for Android][8]

## Programación Asíncrona:
* [Kotlin Coroutines][9]
* [Asynchronous Flow][10]

## Otras librerías:
* [Accompanist for Jetpack Compose][11]
* [Lottie for Jetpack Compose][13]
* [Gson][12]

---

Si se clona el proyecto y quieres ejecutar la app en tu entorno, debes agregar el archivo **google-services.json** configurado con tu proyecto en Firebase, para más información visita la documentación oficial en [como agregar Firebase a tu proyecto en Android][15]

[1]: https://kotlinlang.org/
[2]: https://developer.android.com/jetpack/compose
[3]: https://lottiefiles.com/
[4]: https://firebase.google.com/docs/auth
[5]: https://firebase.google.com/docs/firestore
[6]: https://developer.android.com/topic/libraries/architecture/viewmodel
[7]: https://developer.android.com/jetpack/compose/navigation
[8]: https://developer.android.com/training/dependency-injection/hilt-android
[9]: https://kotlinlang.org/docs/coroutines-overview.html
[10]: https://kotlinlang.org/docs/flow.html
[11]: https://google.github.io/accompanist/
[12]: https://github.com/google/gson
[13]: https://airbnb.io/lottie/#/android-compose
[14]: https://developer.android.com/training/data-storage/shared-preferences
[15]: https://firebase.google.com/docs/android/setup

