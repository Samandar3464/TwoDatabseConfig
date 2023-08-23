//package uz.xb.projectwithtwodb.cron;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import uz.xb.projectwithtwodb.entity.second.ABS;
////import uz.xb.projectwithtwodb.repository.second.ABSRepository;
//import uz.xb.projectwithtwodb.service.UserService;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class UserCron {
//
////    private final ABSRepository aBSRepository;
//    private final UserService userService;
//
////    @Scheduled(cron = "0 22 * * 1-6")
//    @Scheduled(cron = "* * * * * *")
//    public void cron() {
//        List<ABS> abs = userService.fromPtoS();
//        abs.forEach(obj->{
////            aBSRepository.save(obj);
//        });
//    }
//}
