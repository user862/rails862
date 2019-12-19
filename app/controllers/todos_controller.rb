class TodosController < ApplicationController

  def index
    @projects = Project.all
    respond_to do |format|
      format.html
      format.json { render json: @projects, include: [:todos] }
    end
        
        
  end

  def create
        todoParams = params[:todo]
        @project = Project.find(todoParams[:project_id])
	#@todo = @project.todos.create(todo_params)
        todoParams = params[:todo]
        @todo = @project.todos.create(project_id: todoParams[:project_id], text: todoParams[:text], isCompleted: false)
        redirect_to root_path
  end


  def update
    @todo = Todo.find(todo_id_param[:id])
    @todo.update({:id => @todo.id, :isCompleted => !@todo.isCompleted})
    redirect_to root_path
  end

  private
    def todo_params
      params.require(:todo).permit(:text, :project_id)
    end
    def todo_id_param
      params.require(:todo).permit(:id)
    end
end
