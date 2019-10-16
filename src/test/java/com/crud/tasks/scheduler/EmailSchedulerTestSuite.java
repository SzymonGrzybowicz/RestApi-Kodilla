package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTestSuite {
    @InjectMocks
    EmailScheduler emailScheduler;

    @Mock
    TaskRepository taskRepository;

    @Mock
    SimpleEmailService simpleEmailService;

    @Mock
    AdminConfig adminConfig;

    @Test
    public void sentInformationEmail() {
        //Given
        Mockito.when(taskRepository.count()).thenReturn(1L);
        Mockito.when(adminConfig.getAdminMail()).thenReturn("test@test.test");
        //When
        emailScheduler.sentInformationEmail();
        //Then
        Mockito.verify(simpleEmailService).sendDailyMail(Mockito.any());
    }
}
