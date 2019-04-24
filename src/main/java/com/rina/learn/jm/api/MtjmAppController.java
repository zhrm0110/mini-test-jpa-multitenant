package com.rina.learn.jm.api;

import static org.springframework.http.ResponseEntity.ok;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.Min;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rina.learn.jm.AppEntityRepository;
import com.rina.learn.jm.entity.AppEntity;


@RestController
@Validated
@Slf4j
@RequestMapping(value = {"/app"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class MtjmAppController {
    @Autowired
    private AppEntityRepository appEntityRepository;
    @GetMapping(value = "/hello")
    public ResponseEntity<String> helloWorld() {
        return ok("Hello world!!!");
    }

    @GetMapping(value = "/entities")
    public ResponseEntity<List<AppEntity>> getEntities() {
        return ok(appEntityRepository.findAll());
    }
    @PostMapping(value = "/entities")
    public ResponseEntity<AppEntity> postEntities(
            @RequestBody(required = false) AppEntity entity) {
        if (entity.getId()==null) {
            entity.setId(UUID.randomUUID().toString());
        }
        if (!entity.getBpps().isEmpty()) {
            entity.getBpps().forEach(bpp ->{
                bpp.setAppId(entity.getId());
            });
        }
        if (!entity.getCpps().isEmpty()) {
            entity.getCpps().forEach(cpp ->{
                cpp.setAppId(entity.getId());
            });
        }
        entity.getDpp().setAppId(entity.getId());
        if (!entity.getDpp().getEpps().isEmpty()) {
            entity.getDpp().getEpps().forEach(epp ->{
                epp.setAppId(entity.getId());
            });
        }
        if (!entity.getDpp().getFpps().isEmpty()) {
            entity.getDpp().getFpps().forEach(fpp ->{
                fpp.setAppId(entity.getId());
            });
        }

        Date metadataDate= new Date();
        entity.setCreatedAt(metadataDate);
        entity.setChangedAt(metadataDate);
        return ok(appEntityRepository.save(entity));
    }

    @GetMapping(value = "/entities/{id}")
    public ResponseEntity<AppEntity> getEntityById(
            @PathVariable("appId") String appId) {
        AppEntity appEntity = appEntityRepository.findById(appId).get();
        appEntity.getAppD();
        return ok(appEntity);
    }

    @PutMapping(value = "/entities/{id}")
    public ResponseEntity<AppEntity> putEntityById(
            @PathVariable("appId") String appId,
            @RequestBody(required = false) AppEntity entity) {
        if (entity.getId()==null) {
            entity.setId(appId);
        }
        if (!entity.getBpps().isEmpty()) {
            entity.getBpps().forEach(bpp ->{
                bpp.setAppId(appId);
            });
        }
        if (!entity.getCpps().isEmpty()) {
            entity.getCpps().forEach(cpp ->{
                cpp.setAppId(appId);
            });
        }
        entity.getDpp().setAppId(appId);
        if (!entity.getDpp().getEpps().isEmpty()) {
            entity.getDpp().getEpps().forEach(epp ->{
                epp.setAppId(appId);
            });
        }
        if (!entity.getDpp().getFpps().isEmpty()) {
            entity.getDpp().getFpps().forEach(fpp ->{
                fpp.setAppId(appId);
            });
        }
        Date metadataDate= new Date();
        entity.setChangedAt(metadataDate);
        return ok(appEntityRepository.save(entity));
    }

    @DeleteMapping(value = "/entities/{id}")
    public ResponseEntity deleteEntityById(
            @PathVariable("id") String appId) {
        appEntityRepository.deleteById(appId);
        return ResponseEntity.ok().build();
    }
}
