//
//  DismissSegue.swift
//  task5
//
//  Created by user on 15/12/2019.
//  Copyright © 2019 user. All rights reserved.
//

import UIKit

class DismissSegue: UIStoryboardSegue {
    override func perform() {
        self.source.presentedViewController?.dismiss(animated: true, completion: nil)
    }
}
