class TodosController < ApplicationController
  def index
    @projects = Project.all
  end

  def create
    @project = Project.find(params[:todo][:project_id])
    @todo = @project.todos.create(todo_params)
    redirect_to root_path
  end

  def update
  end

  private
    def todo_params
      params.require(:todo).permit(:text, :project_id)
    end
end
