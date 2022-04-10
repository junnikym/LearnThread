# Thread
한개의 프로세스 내부에서 독립적으로 실행되는 하나의 작업 단위이며 
쓰레드를 활용하여 두가지 이상의 일을 동시에 처리할 수 있다. (엄밀히 말하면 동시처럼 보이지만 동시는 아님)

## Process? Thread?

### Process <sub>프로세스</sub>

 - 실행중인 프로그램; 메모리에 적제되어 CPU의 할당을 받을 수 있다.

### Thread <sub>쓰레드</sub>

 - Process의 실행 단위. 
 - 한 Process 내부에서 동작되는 여러 실행의 흐름.
 - Process의 공간이나 자원을 공유할 수 있다.

<p align="center">
    <img src="./images/ThreadAndProcess.png" width="270"/>
</p>

# Java Thread Lifecycle

## Thread Status
```
     ┌────────────┐
     │     New    │ Create Thread  
     └──────┬─────┘
            │  
     ┌──────▼─────┐
     │   Runable  ◀────────────┐
     └──────┬─────┘     ┌──────┴─────┐
            │           │   Blocked  │ (Non-Runnable)
     ┌──────▼─────┐     └──────▲─────┘     
     │   Running  ├────────────┘
     └──────┬─────┘
            │
     ┌──────▼─────┐
     │ Terminator │ 
     └────────────┘
```
 - New : 쓰레드 클래스를 생성했을 때.
 - Runnable : 쓰래드가 준비된 상태
 - Running : 스케줄러에 의해 선택된 쓰래드가 실행
 - Suspended : 동작중인 쓰래드가 일시중지 -> 다시 재개될 수 있음.
 - Blocked : 쓰래드가 대기할 때의 상태 
 - Terminated (Dead) : 쓰래드가 종료됨.

# Java에서 Thread 만들어보기 

Java에서 Thread를 생성하는 방법은 2가지가 있다.

1. <code>Thread</code> 를 상속받아 구현.
2. <code>Runnable</code> Interface를 구현하는 방법

## 1. <code>Thread</code> 를 상속받아 구현.
``` java
public class Sample extends Thread {

    public void run() {
        System.out.println("Run Thread.");
    }
    
}

... 

    Sample thread = new Sample();
    thread.start();
```

## 2. <code>Runnable</code> Interface를 구현하는 방법
``` java
public class Sample extends Runnable {

    public void run() {
        System.out.println("Run Thread.");
    }
    
}

... 

    Thread thread = new Thread( new Sample() );
    thread.start();
```

