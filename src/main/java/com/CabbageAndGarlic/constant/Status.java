package com.CabbageAndGarlic.constant;

public enum Status {
    IN_PROGRESS,  //진행중
    WAITING,  // 작업대기
    CANCELED,  //취소
    COMPLETED, //출하 가능
    SHIPPED, //배송 완료
    PendingShipment,  //배송중
}