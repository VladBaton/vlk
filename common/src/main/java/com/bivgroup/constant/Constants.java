package com.bivgroup.constant;

public class Constants {

    public static final String DATE_PATTERN_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String SUCCESSFULLY_PROCESSED_STATUS = "Обработан успешно";

    public static final String FOOTER_TEXT = "Business intelligence vision, 2025";

    public static final Long SUCCESS_STATUS_CODE = 0L;

    private Constants() {}

    public static class FieldName {

        public static final String RQ_ID = "rqId";

        public static final String RQ_TM = "rqTm";

        private FieldName() {}
    }

    public static class URL {

        public static final String AUTHORIZATION_URL = "http://localhost:8085/account/authorize";

        public static final String CREATE_ACCOUNT_URL = "http://localhost:8085/account/create";

        public static final String GET_USER_DATA_URL = "http://localhost:8085/vlk/account/getUserData";

        public static final String GET_NOTIFICATIONS_BY_CONTRACT_NUMBER = "http://localhost:8085/vlk/notifications/getByContractNumber";

        public static final String GET_NOTIFICATIONS_BY_INSURER_ID = "http://localhost:8085/vlk/notifications/getByClient";
    }
}
