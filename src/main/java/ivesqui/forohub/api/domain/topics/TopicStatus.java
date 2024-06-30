package ivesqui.forohub.api.domain.topics;

public enum TopicStatus {
    CERRADO(0),
    ACTIVO(1),
    ANCLADO(2),
    ARCHIVADO(3),
    DESTACADO(4),
    OCULTO(5),
    REPORTADO(6),
    ELIMINADO(7);

    private final int code;

    TopicStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TopicStatus fromCode(int code) {
        for (TopicStatus status : TopicStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }
}
