package com.example.futsalregsystem.MembershipRequest.Presenter;

import com.example.futsalregsystem.MembershipRequest.Contracts.MemberReqContracts;
import com.example.futsalregsystem.MembershipRequest.DTO.MemberReqDTO;
import com.example.futsalregsystem.MembershipRequest.Model.MemberReqModel;
import com.example.futsalregsystem.MembershipRequest.View.MemberReqView;

import java.util.ArrayList;

public class MemberReqPresenter implements MemberReqContracts.presenter{
    private MemberReqContracts.view view;
    private MemberReqModel model;

    public MemberReqPresenter(MemberReqView memberReqView) {
        this.view = memberReqView;
        model = new MemberReqModel();

    }

    @Override
    public void loadRequest() {
        model.loadRequest(new MemberReqContracts.presenterModelCallback() {
            @Override
            public void loadDetails(ArrayList<MemberReqDTO> details) {
                view.loadAdapter(details);
            }
        });
    }
}
