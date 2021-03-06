package br.ufscar.KDM_MANAGEMENT.discoverer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.modisco.infra.discovery.core.exception.DiscoveryException;
import org.eclipse.modisco.java.discoverer.DiscoverKDMModelFromJavaProject;
import org.eclipse.modisco.java.discoverer.DiscoverKDMModelFromProject;


public class KDMModelDiscover {
	
	/*
	Each MoDisco discoverer responds to a normalized interface and can be called programmatically (see org.eclipse.modisco.infra.discovery.core.IDiscoverer<T>).

	First, add the following plug-in dependencies to your project ( Require-Bundle in your Manifest.MF):

	org.eclipse.gmt.modisco.omg.kdm
	org.eclipse.modisco.kdm.source.discoverer
	org.eclipse.modisco.infra.discovery.core
	You can easily discover a KDM model from a project programmatically:

	DiscoverSourceModelFromJavaElement discoverer = new DiscoverSourceModelFromJavaElement();
	discoverer.discoverElement(javaProject, monitor);
	Resource kdmModel = discoverer.getTargetModel();
	To have a monitor to pass to the discoverElement method, you can either call the discoverer in an Eclipse Job, or pass a new NullProgressMonitor if you don't need progress reporting.

	------------------------------


	First, add the following plug-in dependencies to your project ( Require-Bundle in your Manifest.MF):

	org.eclipse.gmt.modisco.java
	org.eclipse.modisco.java.discoverer
	org.eclipse.modisco.infra.discovery.core
	There are several Java discoverer classes that do the same discovery but on different inputs:

	DiscoverJavaModelFromJavaProject : from an IJavaProject (defined in jdt.core)
	DiscoverJavaModelFromProject : from an IProject
	DiscoverJavaModelFromClassFile : from an IClassFile (defined in jdt.core)
	DiscoverJavaModelFromLibrary : from an IPackageFragmentRoot (for jars; defined in jdt.core)
	You can easily discover a Java model programmatically. For example, to discover a Java model from a Java project:

	DiscoverJavaModelFromJavaProject discoverer = new DiscoverJavaModelFromJavaProject();
	javaDiscoverer.discoverElement(javaProject, monitor);
	Resource javaResource = javaDiscoverer.getTargetModel();
	To have a monitor to pass to the discoverElement method, you can either call the discoverer in an Eclipse Job, or pass a new NullProgressMonitor if you don't need progress reporting.

	Once you have the Java Resource, you can use the standard EMF API to read model elements. For example, to get the root:

	Model javaModel = (Model) javaResource.getContents().get(0);
	To print the list of Java classes in the model:

	EList<ClassFile> classFiles = javaModel.getClassFiles();
	for (ClassFile classFile : classFiles) {
	    System.out.println(classFile.getName());
	}

	----------------------------------------------------------------------------------------------
	Require-Bundle:
	 org.eclipse.gmt.modisco.java;bundle-version="0.9.0",
	 org.eclipse.modisco.java.discoverer;bundle-version="0.9.0",
	 org.eclipse.modisco.infra.discovery.core;bundle-version="0.9.0"

	public void modelDiscovery(String projectName, String savePath){
			try {
				IProject project = 
					ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				IJavaProject javaProject = JavaCore.create(project);
				DiscoverJavaModelFromJavaProject javaDiscoverer = new DiscoverJavaModelFromJavaProject();
				javaDiscoverer.discoverElement(javaProject, new NullProgressMonitor());
				Resource javaResource = javaDiscoverer.getTargetModel();
				FileOutputStream fout = new FileOutputStream(new File(savePath));
				javaResource.save(fout, null);
				fout.close();

			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
			}
		}

	----------------------------------------------------------------------

	IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	DiscoverKDMModelFromProject kdmDiscoverer = new DiscoverKDMModelFromProject();
	kdmDiscoverer.setSerializeTarget(true);
	kdmDiscoverer.discoverElement(project, new NullProgressMonitor());
	Resource kdmResource = kdmDiscoverer.getTargetModel();

	 */
	public void createKDMModel() throws DiscoveryException, IOException, CoreException {

		String ProjectPath = "C:\\Users\\Landi\\git\\testesVariados\\SystemExampleMVC-Sun";
		IProjectDescription description = ResourcesPlugin.getWorkspace().loadProjectDescription(new Path(ProjectPath  + "\\.project")); 
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(description.getName());
		
//		IProject project= ResourcesPlugin.getWorkspace().getRoot().getProject("SystemExampleMVC-Sun");
		
		IJavaProject file = JavaCore.create(project) ;
		
		String locationURIoFTheProject = file.getResource().getLocationURI().toString();

		DiscoverKDMModelFromJavaProject discoverKDMModel =  new DiscoverKDMModelFromJavaProject();

		discoverKDMModel.discoverElement(file, new NullProgressMonitor());

		Resource kdmModel = discoverKDMModel.getTargetModel();

		String kdmLocationToSave = locationURIoFTheProject+"/MODELS_PIM/KDMMODEL.xmi";

		kdmModel.setURI(URI.createURI(kdmLocationToSave));

		kdmModel.save(Collections.EMPTY_MAP);

	}
	
	public void creatKDMModel2() throws DiscoveryException, IOException{
		IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject("SystemExampleMVC-Enquete");
		
		String locationURIoFTheProject = project.getLocationURI().toString();
		
		DiscoverKDMModelFromProject kdmDiscoverer = new DiscoverKDMModelFromProject();
		kdmDiscoverer.setSerializeTarget(true);
		kdmDiscoverer.discoverElement(project, new NullProgressMonitor());
		Resource kdmResource = kdmDiscoverer.getTargetModel();
		
		String kdmLocationToSave = locationURIoFTheProject+"/MODELS_PIM/KDMMODEL.xmi";
		FileOutputStream fout = new FileOutputStream(new File(kdmLocationToSave));
		kdmResource.save(fout, null);
		fout.close();
	}

}
