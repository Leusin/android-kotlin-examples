# android-kotlin-examples

+ __words-app__
  + Fragment and the Navigation Component
+ __unscramble-app__
  + [Store data in ViewModel](https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-viewmodel#0)


## words-app


### Fragemnt Lifecycle
> onCreate > onCreateView > onViewCreate > [ onStart > onPause > onStop ] > onDestroyView > onDestroy


## unscramble-app

+ `Fragment()` - `binding` 정의
+ `onCreateView()` - 결합 객체를 사용하여 레이아웃 XML 확장
+ `onViewCreated()` - 클릭리스너 설정, UI 업데이트


### 앱 아키텍쳐

[ __아키텍쳐 원칙__ ]
+ 관심사 분리: 각각 별개의 책임있는 여러 클래스로 앱을 나누어야 한다
+ 모델에서 UI, 만들기: (가급적이면 지속적인) 모델에서 UI를 만들어야 한다

[ __UI 컨트롤러__ ]
+ `Activity`와 `Fragment`는 UI 컨트롤러
+ UI 컨트롤레는 화면에 뷰를 그리고 사용자 이벤트나 사용자가 이벤트 응답과 동작 제어
+ 엡 데이터 또는 데이터에 관한 모든 의사 결정 로직은 UI 컨롤러 클래스에 포함되어서는 안 된다. (대신 `View Model` 추가) <br><br>


### View Model 추가하기

[ __View Model__ ]
> Class MyViewModel : ViewModel() {  }

+ 뷰에 표시되는 앱 데이터의 모델. 앱의 데이터를 처리 담당
+ Android 프레임워크에서 `Activity`나 `Fragment`가 소멸되고 다시 생성될 떄 폐기되지 않는 앱 관련 데이터를 저장
+ `View Model` 객체는 구성이 변경되는 동안 자동으로 유지되어 보유하고 있는 데이터가 다음 `Activity` 또는`Fragment`에 즉시 사용될 수 있다 <br><br>

[ __Kotlin 속성 위임__ ]
> var \<property-name\> : \<property-type\> by \<delegate-class\>()

+ 변경 가능한 속성 `var` 에는 getter 와 setter 함수 자동 생성된다
+ 읽기 전용 속성 `val` 은 getter 함수만 생성된다
+ 속성 위임(delegate)을 사용하면 getter-setter 책임을 다른 클래스로 넘길 수 있다
+ 대리자(delegate) 클래스가  `by`를 통해 getter 및 setter 함수를 제공하고 변경사항을 처리한다

[ __ViewModel을UI 컨트롤러에 연결하기__ ]
> private val viewModel : MyViewModel by viewModels()

1. Activity 혹은 Fragment 클래스 상단에 `MyViewModel` 유형의 속성 추가
2. `by ciewModels()` Kotilin 속성 위임을 사용하여 `MyViewModel` 초기화


### ViewModel로 데이터 이동

1. ViewModel 클래스 에서 지원속성을 추가한다
> private var _count = 0 <br>
> val count: Int <br>
> &nbsp;&nbsp;get() = _count

2. UI 컨트롤로에서 읽기 전용 변수를 사용한다.
> private var count = viewModel.count

[ __지원 속성__ ]
+ `ViewModel` 내에서는 데이터를 수정할 수 있어야 하므로 데이터는`pricate var` 이어야 한다
+ `ViewModel` 외부에서는 데이터를 읽을 수 있지만 수정할 수 없어야 하므로 'public val'로 노출햐야 한다


### ViewModel의 수명주기
Active나 Fragment의 범위가 유지되는 동안 `ViewModel`을 유지한다. `ViewModel`은 소유자가 화면 회전과 같은 구성 변경으로 인해 소멸되는 경우에도 소멸되지 않는다.

[ __init 블록__ ]
> init {  }
+ Kotlin은 객체 인스턴스 초기화 중 필요한 초기 설정 코드를 배치하는 장소를 제공한다.
+ 이니셜라이저 블록에는 인스턴스가 처음 생성되어 초기화될 떄 실행된다.

### ViewModel 채우기

[ __지연 초기화__ ]
+ Kotlin에서 속성을 나중에 초기화하려면 지연된 초기화를 의미하는`lateinit` 키워드를 사용한다.
+ 변수가 초기화 될 떄까지는 변수에 메모리가 할당되지 않는다 
+ 초기화하기 전에 변수에 엑세스를 하려고 하면 앱이 비정상 종료된다.

[ __배열(Array)__ ]
+ `List`와 비슷하지만 초기화될 떄 고정 크기를 가진다.
+ `Array` 는 크기를 확장하거나 축소할 수 없ek
+ `List` 는 `add()` 함수와 `remove()` 함수가 있어 크기를 늘리고 줄일 수 있다.

### Dialogs

[ __Context__ ]
+ 애플리케이션이나 Activity, Fragment 의 컨텐스트나 현재 상태를 나타내며 관련 정보를 포함하고 있다
+ 일반적으로 리소스, 데이터베이스, 기타 서비스 서비스에 액세스하는 데 사용된다

[ __후행 람다 구문__ ]
+ 전달되는 마지막 인수가 함수이면 괄호 바깥에 람다 표현식을 배치할 수 있다
+ 람다를 관호 안에 배치하거나 바깥에 배치하여 코드를 작성하는 방법이 모두 허용된다