package com.example.microsave.group.service;

import com.example.microsave.exception.ResourceNotFoundException;
import com.example.microsave.group.dto.GroupDTO;
import com.example.microsave.group.entity.Group;
import com.example.microsave.group.repository.GroupRepository;
import com.example.microsave.contribution.repository.ContributionRepository;
import com.example.microsave.loan.repository.LoanRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupService {
    private final GroupRepository repo; private final ContributionRepository contributions; private final LoanRepository loans;
    public GroupService(GroupRepository repo, ContributionRepository contributions, LoanRepository loans){this.repo=repo;this.contributions=contributions;this.loans=loans;}
    private GroupDTO map(Group g){return new GroupDTO(g.getId(),g.getName(),g.getDescription(),g.getCreatedDate(),g.getStatus());}
    private Group get(Long id){return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Group not found: "+id));}
    public List<GroupDTO> all(){return repo.findAll().stream().map(this::map).toList();}
    public GroupDTO one(Long id){return map(get(id));}
    public GroupDTO save(GroupDTO d){Group g=new Group();g.setName(d.name());g.setDescription(d.description());g.setCreatedDate(d.createdDate()==null?LocalDate.now():d.createdDate());g.setStatus(d.status()==null?"ACTIVE":d.status());return map(repo.save(g));}
    public GroupDTO update(Long id,GroupDTO d){Group g=get(id);g.setName(d.name());g.setDescription(d.description());if(d.createdDate()!=null)g.setCreatedDate(d.createdDate());if(d.status()!=null)g.setStatus(d.status());return map(repo.save(g));}
    public void delete(Long id){repo.delete(get(id));}
    public Map<String,Object> pool(Long groupId){get(groupId);BigDecimal total=contributions.totalByGroup(groupId), outstanding=loans.outstandingByGroup(groupId);return Map.of("groupId",groupId,"totalContributions",total,"totalOutstandingLoans",outstanding,"availablePool",total.subtract(outstanding));}
}
