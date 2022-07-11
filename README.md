# android-kotlin-examples
https://developer.android.com/courses/android-basics-kotlin/course

+ __words-app__
  + Fragment and the Navigation Component
+ __unscramble-app__
  + Store data in ViewModel

## words-app

__Fragemnt Lifecycle__
```
onCreate > onCreateView > onViewCreate > [ onStart > onPause > onStop ] > onDestroyView > onDestroy
```


## unscramble-app

+ `Fragment()` - `binding` 정의
+ `onCreateView()` - 결합 객체를 사용하여 레이아웃 XML 확장
+ `onViewCreated()` - 클릭리스너 설정, UI 업데이트

__아키텍쳐 원칙__
+ 관심사 분리: 각각 별개의 책임있는 여러 클래스로 앱을 나누어야 한다
+ 모델에서 UI, 만들기: (가급적이면 지속적인) 모델에서 UI를 만들어야 한다

__UI 컨트롤러__
+ `Activity`와 `Fragment`는 UI 컨트롤러
+ UI 컨트롤레는 화면에 뷰를 그리고 사용자 이벤트나 사용자가 이벤트 응답과 동작 제어
+ 엡 데이터 또는 데이터에 관한 모든 의사 결정 로직은 UI 컨롤러 클래스에 포함되어서는 안 된다. (대신 `View Model` 추가)

__View Model__
+ 뷰에 표시되는 앱 데이터의 모델. 앱의 데이터를 처리 담당
+ Android 프레임워크에서 `Activity`나 `Fragment`가 소멸되고 다시 생성될 떄 폐기되지 않는 앱 관련 데이터를 저장
+ `View Model` 객체는 구성이 변경되는 동안 자동으로 유지되어 보유하고 있는 데이터가 다음 `Activity` 또는`Fragment`에 즉시 사용될 수 있다

__View Model 추가하기__
```
Class MyViewModel : ViewModel() {
}
```

__UI 컨트롤러에 View Model 추가(Kotlin 속성 위임)__
+ 변경 가능한 속성 `var` 에는 getter 함수와 setter setter 함수 자동 생성된다.
+ 읽기 전용 속성 `val` 은 getter 함수만 생성
+ 속성 위임(delegate)을 사용하면 getter-setter 책임을 다른 클래스로 넘길 수 있다.
+ 대리자(delegate) 클래스가  `by`를 통해 getter 및 setter 함수를 제공하고 변경사항을 처리한다.
> var <property-name> : <property-type> by <delegate-class>()

`ViewModel`을UI 컨트롤러에 연결하기 위해 다음과 같이 `ViewModel`에 관한 참조(객체)를 만든다.
1. Activity 혹은 Fragment 클래스 상단에 `MyViewModel` 유형의 속성 추가
2. `by ciewModels()` Kotilin 속성 위임을 사용하여 `MyViewModel` 초기화
```
private val viewModel : MyViewModel by viewModels()
```
