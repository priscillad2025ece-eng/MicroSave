package com.example.microsave.group.controller;

import com.example.microsave.group.dto.GroupDTO;
import com.example.microsave.group.service.GroupService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/groups")
public class GroupController {
    private final GroupService service; public GroupController(GroupService service){this.service=service;}
    @GetMapping public List<GroupDTO> all(){return service.all();}
    @GetMapping("/{id}") public GroupDTO one(@PathVariable Long id){return service.one(id);}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public GroupDTO create(@Valid @RequestBody GroupDTO d){return service.save(d);}
    @PutMapping("/{id}") public GroupDTO update(@PathVariable Long id,@Valid @RequestBody GroupDTO d){return service.update(id,d);}
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
    @GetMapping("/{id}/available-pool") public Map<String,Object> pool(@PathVariable Long id){return service.pool(id);}
}
