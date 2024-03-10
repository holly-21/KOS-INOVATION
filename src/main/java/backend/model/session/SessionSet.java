package backend.model.session;

import java.util.HashSet;
import java.util.Set;

public class SessionSet {

    private static SessionSet ss = new SessionSet();
    private Set<Session> set;

    private SessionSet() {
        set = new HashSet<>();
    }

    public static SessionSet getInstance() {//SessionSet.getInstance() 호출해서 SessionSet 리턴받는다.
        return ss;
    }


    /**
     * 사용자 찾기
     */
    public Session get(String sessionId) {
        for (Session session : set) {
            if (session.getSessionId().equals(sessionId)) {
                return session;
            }
        }
        return null;
    }

    /**
     * 세션 객체들 반환
     * @return 세션
     */
    public Set<Session> getSet() {
        return set;
    }

    /**
     * 로그인 된 사용자 추가
     */
    public void add(Session session) {
        set.add(session);
    }

    /**
     * 현재 세션이 있는지 확인
     * @return
     */
    public Session getCurrentSession() {
        if (set.isEmpty()) {
            return null;
        }
        return set.iterator().next();
    }

    /**
     * 사용자 제거 - 로그아웃
     */
    public void remove(Session session) {
        set.remove(session);
    }
}
