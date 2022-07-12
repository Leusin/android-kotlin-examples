# android-kotlin-examples

+ [__1. words-app__](#1-words-app)
  + Fragment and the Navigation Component
+ [__2. unscramble-app__](#2-unscramble-app)
  + [Store data in ViewModel](https://developer.android.com/codelabs/basic-android-kotlin-training-viewmodel?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-viewmodel#0)
  + [Use LiveData with ViewModel](https://developer.android.com/codelabs/basic-android-kotlin-training-livedata?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-livedata#0)

## 1. words-app

### Fragemnt Lifecycle
> onCreate > onCreateView > onViewCreate > [ onStart > onPause > onStop ] > onDestroyView > onDestroy


## 2. unscramble-app

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


###  Submit

[ __텍스트 필드 오류 표시__ ]
+ 머티리얼 텍스필드에는 `TextInputLayout`에 요류 메시지를 표시하는 기능이 내장되어있다
+ 텍스트 필드에 오류를 표시하려면 코드에서 동적 또는 레이아웃 파일에서 정적 오류 표시하면 된다.
> // Set error text <br>
> passwordLayout.error = getString(R.string.error) <br>
> // Clear error text <br>
> passwordLayout.error = null  <br>

+ 시작 코드에는 `setErrorTextField(error: Boolean` 가 이미 정의 되어 있어 텍스트 필드에 오류를 설정할 수 있다
+ 오류 텍스트를 필드에 표시할지 예부에 따라 `true`나 `false`를 입력 매개변수로 사용하여 호출할 수 있다
> private fun setErrorTextField(error: Boolean) {
> &nbsp;&nbsp;if (error) {
> &nbsp;&nbsp;&nbsp;&nbsp;binding.textField.isErrorEnabled = true
> &nbsp;&nbsp;&nbsp;&nbsp;binding.textField.error = getString(R.string.try_again)
> &nbsp;&nbsp;} else {
> &nbsp;&nbsp;&nbsp;&nbsp;binding.textField.isErrorEnabled = false
> &nbsp;&nbsp;&nbsp;&nbsp;binding.textInputEditText.text = null
> &nbsp;&nbsp;}
> }


### LiveData

+ 수명 주기를 인식하는 관측 가능한 홀더 클래스이다
+ `LiveData`에 관찰자(Observer)를 연결하면 관찰자는 `LifecycleOwner`(주로 Activity 나 Fragment)와 연결된다
+ 객체의 보유한 데이터가 관찰자에게 알림을 제공한다.
+ `Livedata`를 사용하면 앱 초기화 시 UI를 업데이트하기 위해 여러위치에 있는 메서드를 호출하지 않고 관찰자에서 한 번만 호출하면 된다.

__[ MutableLiveData ]__
+ 변경 가능한 버전의 `LiveData` 이다.
+ 내부에 저장된 데이터의 값을 변경할 수 있다.

## LiveData 로 데이터 래핑하기

__[ 1단계: LiveData로 점수 및 단어 래핑 ]__
+ `LiveData` 및 `MutableLiveData` 객체의 값은 동일하게 유지되고 이 객체에 지정된 데이터만 변경되기 때문에 클래스 변수의 속성을 `val` 로 한다
+ 변수의 데이터 유형을 `MutableLiveData` 로, 지원 필드 유형을 LiveData<[property]>로 변경한다
  + private 필드는 _백업 저장소_ 또는 _지원 필드_라고 한다.
> private val _score = __MutableLiveData(0)__<br>
> val score: __LiveData<Int>__<br>
> &nbsp;&nbsp;get() = _score<br><br>
> private val _count = __MutableLiveData(0)__<br>
> val count: __LiveData<Int>__<br>
> &nbsp;&nbsp;get() = _count

+ `LiveData` 객체 내의 데이터에 액세스 하려면 `value` 속성을 사용한다
> _score.__value__ = 0
  + `LifecycleOwner` 에서도 `value` 속성을 사용하여 `score` 의 값에 엑세스할 수 있도록 한다
> viewModel.score.value

+ 예제의 `_score` 은 더 이상 정수가 아닌 `LiveData` 이기 떄문에 Kotlin 함수 plus()를 사용하여 `_score` 값을 늘릴 수 있다
> _score.value = (_score.value)?.plus(SCORE_INCREASE)

+ Kotlin함수`inc()` 를 사용하여 null 에 안전하게 값을 1씩 증분할 수 있다.
> _count.value = (_currentWordCount.value)?.inc()

__[ 2단계: 관찰자 연결하기 ]__
+ Fragment의 경우 `onViewCreated()` 메서드에서 `LiveData`의 관찰자를 연결한다
+ `viewLifecycleOwner` 를 첫 번째 매개변수로 `observe()` 매서드에 전달하고 두 번째 매개변수로 람다식 표현을 전달한다.
  + 람다 표현식은 선언되지 않았지만 즉시 표현식으로 전될되는 익명삼수로 항상 중괄호 {} 로 묶는다
+ 이 매개변수를 사용하면 `LiveData` 가 `viewLifecycleOwner`의 수명주기를 인식하고 활성 상태(`STARTED`, `RESUMED`) 때 관찰자에게 알릴 수 있다.
> viewModel.score.observe(viewLifecycleOwner,
> &nbsp;&nbsp;{ newScore ->
> })

## 데이터 결합과 함께 LiveData 사용하기

__[ 뷰 결합 ]__
+ 뷰 결합은 코드에서 뷰에 더 쉽게 엑서스할 수 있는 기능으로, 각 XML 레이아웃 파일의 결합 클레스를 생성한다
+ 뷰를 코드에 바인딩 할 수 있지만 코드를 뷰에 바인딩 할 수 없는 단방향 결합이다
  + 뷰 -> 코드 [o]
+ 뷰 결합을 사용하면 뷰(레이아웃 파일)에서 앱 데이터를 참조할 수 없다.
  + 뷰 <- 코드 [x]
  + 이 작업은 데이텨 결합을 사용하면 된다.
>binding.textViewUnscrambledWord.text = newWord<br>
> binding.score.text = getString(R.string.score, newScore)

__[ 데이터 결합 ]__
+ 데이터 결합 라이브러리는 Android Jetpack 라이브러리의 일부이다.
+ 데이터 결합은 선언적 형식을 사용하며 레이아웃의 UI 구성요소를 앱의 데이터 소스에 바인딩 한다
  + 코드에서 데이터를 뷰 + 뷰 결합에 결합(뷰를 코드에 결합)하는 것이다
  + 뷰 <- 코드 [o]

+ UI 컨트롤러에서 예
> binding.textViewUnscrambledWord.text = viewModel.currentScrambledWord

+ XML 파일에서 예
> android:text="@{gameViewModel.currentScrambledWord}"

__[ 데이터 결합 사용 이점 ]__
+Activity에서 많은 UI 프레임워크를 호출을 삭제할 수 있어 파일이 더욱 단순해지고 더손쉬운 유지관리가 가능해진다
+ 앱성능이 향상되며 메모리 누수 및 null 포인터 예외를 방지할 수 있다

__[ 1단계: 뷰 결합을 데이터 결합으로 변경하기 ]__
+ `build.gradle(Module)` 파일의 `buildFeatures` 섹션에서 `dataBinding` 속성을 사용 설정합니다.
```
buildFeatures {
   viewBinding = true
}
```
대신
```
buildFeatures {
   dataBinding = true
}
```
로 바꾼다

+ 'build.gradle(Module)' 파일에서 `kotlin-kapt` 플러그인을 적용한다
```
plugins {
   id 'com.android.application'
   id 'kotlin-android'
   id 'kotlin-kapt'
}
```

__[ 12단계: 레이아웃 파일을 데이터 결합 레이아웃으로 변환하기 ]__ 
+ 루트 요소를 `<layout>` 태그로 래핑하고 네임스페이스 정의(`xml:`...)를 새 루트 요소로 이동한다. 
+ `<layout>` 태그 내부에 `<data></data>` 태그를 추가한다
  + Android 스튜디오에서는 루트 요소를 마우스 오른쪽 버튼으로 클릭하고 Show Context Actions > Convert to data binding layout을 선택하면 자동으로 간편하게 추가할 수 있다
+ 시작부분에서 결합 데이터를 사용하도록 binding 변수의 인스턴스턴스화를 변경한다
> binding = GameFragmentBinding.inflate(inflater, container, false)
대신
> binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)
로 바꾼다