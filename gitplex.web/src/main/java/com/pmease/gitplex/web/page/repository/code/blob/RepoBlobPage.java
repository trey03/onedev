package com.pmease.gitplex.web.page.repository.code.blob;

import org.apache.wicket.RestartResponseException;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.pmease.commons.git.GitPath;
import com.pmease.gitplex.core.model.Repository;
import com.pmease.gitplex.web.page.repository.NoCommitsPage;
import com.pmease.gitplex.web.page.repository.RepositoryPage;

@SuppressWarnings("serial")
public class RepoBlobPage extends RepositoryPage {

	private static final String PARAM_REVISION = "revision";
	
	private static final String PARAM_PATH = "path";
	
	protected IModel<Repository> repoModel;
	
	protected String revision;
	
	protected String path;
	
	public RepoBlobPage(PageParameters params) {
		super(params);
		
		if (!getRepository().git().hasCommits()) 
			throw new RestartResponseException(NoCommitsPage.class, paramsOf(getRepository()));
		
		revision = GitPath.normalize(params.get(PARAM_REVISION).toString());
		path = GitPath.normalize(params.get(PARAM_PATH).toString());
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
	}
	
	public static PageParameters paramsOf(Repository repository, String revision, String path) {
		PageParameters params = paramsOf(repository);
		params.set(PARAM_REVISION, revision);
		params.set(PARAM_PATH, path);
		return params;
	}
	
}
