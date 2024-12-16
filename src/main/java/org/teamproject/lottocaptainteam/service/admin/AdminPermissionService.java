package org.teamproject.lottocaptainteam.service.admin;

import java.io.IOException;


public interface AdminPermissionService {
    boolean adminPermissionAcceptedTo(String id) throws IOException;
}
