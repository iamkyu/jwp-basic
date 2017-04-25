#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* ServletContextListener, ServletContextAttributeListener, ServletRequestListener, ServletRequestAttributeListener, HttpSessionListener, HttpSessionAttributeListener, HttpSessionIdListener
* 위 인터페이스 중 하나를 구현하는 @WebListener 클래스를 만들면 해당 이벤트 시점에 특정 코드를 실행할 수 있다.
* ContextLoaderListener 클래스는 서블릿 컨텍스트가 실행 되는 시점에 `jwp.sql` 의 DDL 과 DML 을 실행한다.
*  컨텍스트가 로드(생성?) 되면서 서블릿을 초기화 하는데 이 때 loadOnStartup 이 0 이상인 (우선순위가 높은) 서블릿을 읽어들인다.
* DispatcherServlet 의 순서가 1이므로 해당 서블릿의 init() 메소드를 호출한다.

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* 요청 - 스레드 생성 - 뭔가 핸들러를 통함~~~ - 요청을 필터링 함 - 디스패처서블릿의 service() 메소드를 실행한다 
 - 서비스 안에서는 http 요청 uri 에 따른 컨트롤러를 찾는다


#### 7. next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생하는 이유에 대해 설명하라.
* 질문객체와 답변이 들어 있는 리스트가 클래스변수로 선언되어 있었는데 멀티스레드 상황에서 변수를 공유할 수도 있다. 따라서 두 변수를 메소드 지역 변수로 변경하였다 .