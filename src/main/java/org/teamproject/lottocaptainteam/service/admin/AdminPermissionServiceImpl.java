package org.teamproject.lottocaptainteam.service.admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.teamproject.lottocaptainteam.service.constant.FilePath;

public class AdminPermissionServiceImpl implements AdminPermissionService {

    private final List<String> registeredAdministers;

    public AdminPermissionServiceImpl(FilePath source) throws IOException {
        registeredAdministers = crawledAdministersBy(source);
    }

    private void updateAdministerList() {
        
    }

    @Override
    public boolean adminPermissionAcceptedTo(String id) throws IOException {
        return registeredAdministers.contains(id);
    }

    private List<String> crawledAdministersBy(FilePath source) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(source.getPath()));
        List<String> administers = new ArrayList<>();

        String registeredId = br.readLine();
        while(registeredId != null) {
            administers.add(registeredId);
            registeredId = br.readLine();
        }
        br.close();

        return administers;
    }
}

