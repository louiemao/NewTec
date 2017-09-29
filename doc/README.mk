#
 <button type="button" ng-if="u.id!='6e5afb1d-50e1-45fe-b6fe-b9e399415387'"
                            ng-click="removeUserById(u.id)" class="btn btn-danger" has-permission="'user:delete'">删除
                    </button>
ng-if,has-permission 一起用的时候会全部不显示