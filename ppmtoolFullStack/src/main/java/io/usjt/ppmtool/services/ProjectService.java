package io.usjt.ppmtool.services;

import io.usjt.ppmtool.domain.Project;
import io.usjt.ppmtool.exceptions.ProjectIdException;
import io.usjt.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID '"+project.getProjectIdentifier().toUpperCase()+"' already exists");
        }

    }


    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '"+projectId+"' does not exist");

        }


        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public Project findByProjectIdentifier(String projectIdentifer){

        Project project = projectRepository.findByProjectIdentifier(projectIdentifer.toUpperCase());

        if(project == null){
            throw new ProjectIdException("Project ID '" + projectIdentifer.toUpperCase() + "' does not exists!");
        }

        return project;
    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }
}
