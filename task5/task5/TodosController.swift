//
//  TodosController.swift
//  task5
//
//  Created by user on 15/12/2019.
//  Copyright © 2019 user. All rights reserved.
//

import UIKit

class TodosController: UITableViewController {
    @IBOutlet weak var newTodoOutlet: UIBarButtonItem!
    
    var projects = [(id: 0,title:"Семья",todos: [(id: 0, text: "Купить сыр", isCompleted: false),(id: 1, text: "Купить молоко Купить молоко Купить молоко Купить молоко Купить молоко Купить молоко Купить молоко ", isCompleted: false),(id: 2, text: "Забрать обувь", isCompleted: true)]),(id: 1,title:"Работа",todos: [(id: 3, text: "Написать отчет", isCompleted: true),(id: 4, text: "Пойти на встречу", isCompleted: false)])]
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        newTodoOutlet.tintColor = UIColor.white
        //self.navigationController?.navigationBar.barStyle = UIBarStyle.blackTranslucent
        self.navigationController?.navigationBar.barTintColor  = UIColor(hex: "#3aafdaff");
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let navVC = segue.destination as? UINavigationController
        let tableVC = navVC?.topViewController as! NewTodoController
        var projectsNames: [ (id: Int,title: String) ] = projects.map { (id: $0.id,title: $0.title ) }
        tableVC.projectsNames = projectsNames
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return projects.count
    }
    
    override func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        
        let projectTitle = UITableViewHeaderFooterView()
        projectTitle.textLabel?.text = projects[section].title
        projectTitle.textLabel?.font = UIFont.boldSystemFont(ofSize: 16.0)
        return projectTitle
    }
    
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
     return projects[section].todos.count
     
     }
     
     override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
     let cell = self.tableView.dequeueReusableCell(withIdentifier: "tCell") as! TodoCell
        let attributeString: NSMutableAttributedString =  NSMutableAttributedString(string: projects[indexPath.section].todos[indexPath.row].text)
        let status = projects[indexPath.section].todos[indexPath.row].isCompleted
        if status {
            attributeString.addAttribute(NSAttributedString.Key.strikethroughStyle, value: 2, range: NSMakeRange(0, attributeString.length))
        }
        cell.textTodo.attributedText = attributeString
        
        cell.todoCheckbox.setOn(status, animated: true)
        cell.todoCheckbox.boxType = .square
        cell.todoCheckbox.onAnimationType = .flat
        cell.todoCheckbox.onFillColor = UIColor(hex: "#3aafdaff")!
        cell.todoCheckbox.onCheckColor = UIColor.white
        cell.todoCheckbox.onTintColor = UIColor(hex: "#3aafdaff")!
     return cell
     }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let cell: TodoCell = tableView.cellForRow(at: indexPath) as! TodoCell
        /*
         
        send
         
        */
        let status: Bool = projects[indexPath.section].todos[indexPath.row].isCompleted
        projects[indexPath.section].todos[indexPath.row].isCompleted = !status
        let attr: NSMutableAttributedString =  NSMutableAttributedString(string: projects[indexPath.section].todos[indexPath.row].text)
        if(status){
            //to-uncheck
            attr.removeAttribute(NSAttributedString.Key.strikethroughStyle, range:NSMakeRange(0, attr.length))
        } else {
           //to-check
            attr.addAttribute(NSAttributedString.Key.strikethroughStyle, value: 2, range: NSMakeRange(0, attr.length))
            
        }
        cell.textTodo.attributedText = attr
        cell.todoCheckbox.setOn(!status, animated: true)
        
        
    }
    
    
}
