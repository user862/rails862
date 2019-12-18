//
//  Project.swift
//  task5
//
//  Created by user on 18/12/2019.
//  Copyright © 2019 user. All rights reserved.
//

import Foundation

struct Project: ResponseObjectSerializable, ResponseCollectionSerializable, CustomStringConvertible {
    let id: Int
    let title: String
    var todos: [Todo]
    
    var description: String {
        return "Project: { id: \(id), title: \(title), todos: \(todos) }"
    }
    
    init?(response: HTTPURLResponse, representation: Any) {
        guard
            let representation = representation as? [String: Any],
            let id = representation["id"] as? Int,
            let todosAny = representation["todos"],
            let title = representation["title"] as? String
            else { return nil }
        
        self.id = id
        self.title = title
        self.todos = Todo.collection(from: response, withRepresentation: todosAny)
    }
    
}

struct Todo: ResponseObjectSerializable, ResponseCollectionSerializable, CustomStringConvertible {
    let id: Int
    let text: String
    var isCompleted: Bool
    
    var description: String {
        return "Todo: { id: \(id), text: \(text), isCompleted: \(isCompleted)}"
    }
    
    init?(response: HTTPURLResponse, representation: Any) {
        guard
            let representation = representation as? [String: Any],
            let id = representation["id"] as? Int,
            let text = representation["text"] as? String,
            let isCompleted = representation["isCompleted"] as? Bool
            else { return nil }
        
        self.id = id
        self.text = text
        self.isCompleted = isCompleted
    }
}
