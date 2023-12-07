# CONTROLLER, SERVICE, REPOSITORY 

* CLIENT -> CONTROLLER -> SERVICE -> REPOSITORY ->DB
  *  CONTROLLER 
      * MODEL이 데이터를 어떻게 처리할지 알려주는 역할
      * 사용자에의해 클라이언트가 보낸 데이터가 있으면 모델을 호출하기 전에 적절히 가공을 하고 모델을 호출
      * 모델이 업무수행을 완료하면 그 결과를 VIEW에 전달
      * 요청에 따라 어떤 처리를 할지 결정을SERVICE에 넘겨줌
      * @Controller : 주로 view를 반환하기 위해 사용, @ResponsBody와 같이 사용하면 RestController와 똑같은 기능을 수행
      * @RestController : json, xml 형태로 객체 데이터 반환을 목적으로 함(@ResponseBody 가 붙은 효과를 지니게됨)
    
  * SERVICE
    * Client 가 Request를 보낸다.(ajax 등)
    * Controller는 넘어온 요청을 처리하기 위해 Service를 호출한다
    * Service는 알맞은 정보를 가공하여 Controller에게 데이터를 넘긴다.
    * Controller는 Service의 결과물을 Client에게 전달해준다.
    * Service가 알맞은 정보를 가공하는 과정을 "비즈니스로직을 수행한다."라고한다.
  * REPOSITORY
    * Entity에 의해 생성된 DB에 접근하는 메서드 들을 사용하기 위한 인터페이스
    * Reopsiroty 인터페이스에 JpaRepository를 상속해 주면 된다.