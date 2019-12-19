//
//  NewTodoController.swift
//  task5
//
//  Created by user on 16/12/2019.
//  Copyright © 2019 user. All rights reserved.
//

import UIKit
import Alamofire

class NewTodoController: UITableViewController {
    
    @IBOutlet weak var addTodoButtonOutlet: UIBarButtonItem!
    
    @IBAction func addTodoButton(_ sender: Any) {
        let textEditIndexPath : IndexPath = IndexPath(row: 0, section: 0)
        let textEditcell: TextEditCell = tableView.cellForRow(at: textEditIndexPath) as! TextEditCell
        let text = textEditcell.textEdit.text
        if text != "" && selectedCellInd != nil {
            // sending
            let parameters: [String: AnyObject] = ["project_id": projectsNames[selectedCellInd!.row].id as AnyObject,"text": text! as AnyObject]
            Alamofire.request(url+create, method: .post,parameters: parameters, encoding: JSONEncoding.default)
                .responseJSON{ response in
                    switch response.result {
                    default:
                        self.navigationController?.popViewController(animated: true)
                        self.dismiss(animated: true, completion: nil)
                        break
                    }
            }
            
        }
    }
    
    @IBOutlet weak var dismissButtonOutlet: UIBarButtonItem!
    @IBAction func dismissButton(_ sender: Any) {
        
        
        navigationController?.popViewController(animated: true)
        dismiss(animated: true, completion: nil)
    }
    
    var projectsNames: [(id: Int,title: String)] = [(title: "",id: 0)]
    var selectedCellInd: IndexPath?
    let url: String = "http://polar-fortress-89756.herokuapp.com/"
    let create = "todos/create"
    let url2 = "https://enp3dcgjtpdnq.x.pipedream.net"
    
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        addTodoButtonOutlet.tintColor = UIColor.white
        dismissButtonOutlet.tintColor = UIColor.white
        self.navigationController?.navigationBar.barStyle = UIBarStyle.blackTranslucent
        self.navigationController?.navigationBar.barTintColor  = UIColor(hex: "#3aafdaff");
        // Do any additional setup after loading the view.
    }
    
    override func viewWillAppear(_ animated: Bool) {
        tableView.reloadData()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return section == 0 ? 1 : projectsNames.count
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 2
    }
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.section == 0 {
            let  height : CGFloat = 90
            return height
        }
        return 50
    }
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if indexPath.section == 0 {
            let cell = self.tableView.dequeueReusableCell(withIdentifier: "todoTextEdit") as! TextEditCell
            
            cell.textEdit.attributedPlaceholder = NSAttributedString(string: "Введите свою задачу...", attributes: [NSAttributedString.Key.foregroundColor: UIColor(hex: "#aabobcff")])
            
            return cell
        }
        let cell = self.tableView.dequeueReusableCell(withIdentifier: "projectCell") as! TodoCell
        cell.actionBlock = {
            self.changePickedProject(indexPath: indexPath)
        }
        cell.textTodo.text = projectsNames[indexPath.row].title
        cell.todoCheckbox.setOn(false, animated: true)
        cell.todoCheckbox.boxType = .square
        cell.todoCheckbox.onAnimationType = .flat
        cell.todoCheckbox.onFillColor = UIColor(hex: "#3aafdaff")!
        cell.todoCheckbox.onCheckColor = UIColor.white
        cell.todoCheckbox.onTintColor = UIColor(hex: "#3aafdaff")!
        
        return cell
    }
    
    func changePickedProject(indexPath: IndexPath){
        if indexPath.section == 1 && indexPath != selectedCellInd {
            let cell: TodoCell = tableView.cellForRow(at: indexPath) as! TodoCell
            cell.todoCheckbox.on = true
            if selectedCellInd != nil {
                (tableView.cellForRow(at: selectedCellInd!) as! TodoCell).todoCheckbox.on = false
            }
            selectedCellInd = indexPath
        }
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        changePickedProject(indexPath: indexPath)
    }
    
    
    
    
}
