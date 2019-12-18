//
//  TodoCell.swift
//  task5
//
//  Created by user on 15/12/2019.
//  Copyright © 2019 user. All rights reserved.
//

import UIKit
import BEMCheckBox

class TodoCell: UITableViewCell, BEMCheckBoxDelegate {
    @IBOutlet weak var todoCheckbox: BEMCheckBox!
    @IBOutlet weak var textTodo: UILabel!
    
  override func awakeFromNib() {
        super.awakeFromNib()
        todoCheckbox.delegate = self
    }
    
    var actionBlock: (() -> Void)? = nil

    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func didTap(_ checkbox: BEMCheckBox){
        actionBlock?()
        /*if checkbox.on {
            checkbox.setOn(false, animated: false)
        } else {
            checkbox.setOn(true, animated: false)
        }*/
    }
}
